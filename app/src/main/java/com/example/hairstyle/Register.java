package com.example.hairstyle;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private Button buttonRegister;
private EditText  input_name_register ,input_email_register ,input_login_register,input_password_register,input_check_password_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro");


        input_name_register  = findViewById(R.id.id_input_name_register);

        input_email_register  = findViewById(R.id.id_input_email_register);

        input_login_register = findViewById(R.id.id_input_login_register);

        input_password_register = findViewById(R.id.id_input_password_register);

        input_check_password_register = findViewById(R.id.id_input_check_password);


        buttonRegister = findViewById(R.id.idButtonSave);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPost();


            }
        });
    }

    public void getPost (){

        String name = input_name_register.getText().toString();
        String username = input_login_register.getText().toString();
        String password = input_password_register.getText().toString();
        String endPoint = "https://demoope.herokuapp.com/api/usuarios";


        ServiceTask serviceTask = new ServiceTask(this,endPoint,name,username,password);
        serviceTask.execute();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}
