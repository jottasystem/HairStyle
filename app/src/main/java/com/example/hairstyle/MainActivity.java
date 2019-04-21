package com.example.hairstyle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private String login_main;
    private String password_main;
    private String baseUrl;

    TextView textRegisterClick;
    TextView textForgotPassword;

    EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        buttonLogin = findViewById(R.id.idButtonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    login_main = login.getText().toString();
                    password_main = password.getText().toString();



                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);



              /*  Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            System.out.println(" Request Data "+response.toString());

                            boolean sucess = jsonObject.getBoolean("sucess");

                            if(sucess){

                                String name = jsonObject.getString("name");
                                String username = jsonObject.getString("username");


                                Intent intent = new Intent(MainActivity.this, home.class);
                                intent.putExtra("name",name);
                                intent.putExtra("username",username);

                                MainActivity.this.startActivity(intent);

                                //startActivity(intent);

                            }else{
                                AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);

                                builder.setMessage("Usuario ou senha incorreto.")
                                        .setNegativeButton("Retry",null)
                                        .create().show();

                            }
                        } catch (JSONException e) {
                            //e.printStackTrace();
                        }
                    }
                };


                LoginRequest loginRequest = new LoginRequest(login_main,password_main, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);


*/
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


    /**
     * Open a new activity window.
     */
    private void goToSecondActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", login_main);
        bundle.putString("password", password_main);
        bundle.putString("baseUrl", baseUrl);

        Intent intent = new Intent(this, home.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
