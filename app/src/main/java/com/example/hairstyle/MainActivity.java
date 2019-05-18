package com.example.hairstyle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hairstyle.CRUD.Create;
import com.example.hairstyle.CRUD.Insert;
import com.example.hairstyle.CRUD.Read;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button buttonLogin;
    private String login_main;
    private String password_main;
    private String baseUrl;
    private CheckBox checkBox;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    TextView textRegisterClick;
    TextView textForgotPassword;
    EditText login, password;
    String tkn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tkn= FirebaseInstanceId.getInstance().getToken();

        System.out.println("TOKEN"+tkn);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getSupportActionBar().hide();

        baseUrl = "http://demoope.herokuapp.com/api/usuarios";

        login = (EditText) findViewById(R.id.id_input_login_main);
        password = (EditText) findViewById(R.id.id_input_password);
        checkBox = (CheckBox) findViewById(R.id.id_checkBox);




        //persistencia
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // mPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        mEditor =mPreferences.edit();





        checkSharedPreferences();

        buttonLogin = findViewById(R.id.idButtonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    login_main = login.getText().toString();
                    password_main = password.getText().toString();



                if(haveConnection()){
                    if(login_main.isEmpty() || password_main.isEmpty()){
                        Toast.makeText(MainActivity.this, "Preencha Login e Senha.",Toast.LENGTH_LONG).show();

                    }else{

                        if(checkBox.isChecked()){
                            mEditor.putString(getString(R.string.checkbox),"True");
                            mEditor.commit();

                            //save login

                            mEditor.putString(getString(R.string.name),login_main);
                            mEditor.commit();

                            //save password

                            mEditor.putString(getString(R.string.password),password_main);
                            mEditor.commit();
                        }else{
                            mEditor.putString(getString(R.string.checkbox),"False");
                            mEditor.commit();

                            //save login

                            mEditor.putString(getString(R.string.name),"");
                            mEditor.commit();

                            //save password

                            mEditor.putString(getString(R.string.password),"");
                            mEditor.commit();
                        }
                        new Notify().execute();

                        getUser(login_main,password_main);


                    }

                }
                else if (!haveConnection()){

                    System.out.println("ENTROUuu n tem net");

                    Read r = new Read(getApplicationContext());

                    Toast.makeText(MainActivity.this, "Não possui internet, se  possuir dados locamente você poderar efetuar o login",Toast.LENGTH_LONG).show();

                    ArrayList<Pessoa> pessoaArrayList = r.getPessoas();

                 if(pessoaArrayList.size() >= 0){
                     Intent intent = new Intent(getApplicationContext(),home.class);
                     startActivity(intent);

                    }
                 }
            }
        });

        textRegisterClick = findViewById(R.id.idTextRegister);
        textRegisterClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

        textForgotPassword = findViewById(R.id.idTextForgotPassword);
        textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);
            }
        });

    }


    private  void checkSharedPreferences (){
       String checkbox = mPreferences.getString(getString(R.string.checkbox),"false");
        String name = mPreferences.getString(getString(R.string.name),"");
        String password_login = mPreferences.getString(getString(R.string.password),"");

        login.setText(name);
        password.setText(password_login);

        if(checkbox.equals("True")){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

    }

    public class Notify extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                URL url = new URL("https://fcm.googleapis.com/fcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=AIzaSyCfuOEIvgHSZ8ml4_PDOVfE8DZS4Afb53M");
                conn.setRequestProperty(" -Type", "application/json");

                JSONObject json = new JSONObject();

                json.put("to", tkn);


                JSONObject info = new JSONObject();
                info.put("title", "HairStyle");   // Notification title
                info.put("body", "Entre em contato com nosso salão para ganhar desconto em nossa promoção dos dias da Mãe"); // Notification body

                json.put("notification", info);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();
                conn.getInputStream();

            }
            catch (Exception e)
            {
                Log.d("Error",""+e);
            }

            return null;
        }
    }


    private void getUser(String username, String password){

        String url = "https://demoope.herokuapp.com/api/usuarios/"+username+"/"+password;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String userLogin = response.getString("status");

                            if(userLogin == "true"){
                                goToSecondActivity();
                            }else{
                                Toast.makeText(MainActivity.this, "Usuario ou senha incorreto.",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(request);


    }
    private void goToSecondActivity() {
        Intent intent = new Intent(this, home.class);
        Bundle data = new Bundle();
        data.putString("username", login_main);
        intent.putExtras(data);
        startActivity(intent);
    }


    private  boolean  haveConnection(){
        boolean have_Wifi = false;
        boolean have_Mobile_Data = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for( NetworkInfo info:networkInfos){
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                    have_Wifi=true;

            if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                if(info.isConnected())
                    have_Mobile_Data =  true;

        }

        return have_Mobile_Data || have_Wifi;

    }
}
