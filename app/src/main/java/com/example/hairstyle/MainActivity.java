package com.example.hairstyle;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
                    getUser(login_main,password_main);

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
        Bundle bundle = new Bundle();
        bundle.putString("username", login_main);

        Intent intent = new Intent(this, home.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
