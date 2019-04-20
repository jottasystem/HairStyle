package com.example.hairstyle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;

    TextView textRegisterClick;
    TextView textForgotPassword;

    EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



        login = (EditText) findViewById(R.id.id_input_login_main);
        password = (EditText) findViewById(R.id.id_input_password);


        buttonLogin = findViewById(R.id.idButtonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(),home.class);

                System.out.println("LOGINNN"+login);
                intent.putExtra("login",login.getText().toString());

                startActivity(intent);

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


}
