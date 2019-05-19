package com.example.hairstyle;

import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairstyle.CRUD.Create;
import com.example.hairstyle.CRUD.Insert;

public class Register extends AppCompatActivity {
    DBAdapter DB;
    public static final String TAG = "MyTAG";
    private Button buttonRegister;
    private EditText  input_name_register ,input_email_register ,input_login_register,input_password_register,input_check_password_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cadastro");

        Create c = new Create(getApplicationContext());

        c.createTable();

        input_name_register  = (EditText) findViewById(R.id.id_input_name_register);

        input_email_register  = (EditText) findViewById(R.id.id_input_email_register);

        input_login_register = (EditText)  findViewById(R.id.id_input_login_register);

        input_password_register = (EditText) findViewById(R.id.id_input_password_register);

        input_check_password_register = (EditText)  findViewById(R.id.id_input_check_password);



        DB = new DBAdapter(getBaseContext());



        buttonRegister = findViewById(R.id.idButtonSave);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(haveConnection()){
                     getPost();
                    input_name_register.getText().clear();
                    input_email_register.getText().clear();
                    input_login_register.getText().clear();
                    input_password_register.getText().clear();
                    input_check_password_register.getText().clear();

                }else if (!haveConnection()){
                    Toast.makeText(Register.this, "Você não está conectado com internet, seus dados serão salvado localmente.",Toast.LENGTH_SHORT).show();

;

                        inserData();




                }
            }
        });


    }




    public  void inserData(){
        if(input_login_register.getText().toString()  != "" || input_password_register.getText().toString()  != "" ){
            System.out.println("ENTROu");
            DB.inserUser(input_login_register.getText().toString(),input_password_register.getText().toString());
            Toast.makeText(Register.this, "Salvo localmente",Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(),MainActivity.class);

            startActivity(intent);
        }
    }

    public void getPost (){

        String name = input_name_register.getText().toString();
        String username = input_login_register.getText().toString();
        String password = input_password_register.getText().toString();
        String endPoint = "https://demoope.herokuapp.com/api/usuarios";
        String message = "Efetuando registro";
        String backMessage = "Registro efetuado com sucesso!";

        ServiceTask serviceTask = new ServiceTask(this,endPoint,name,username,password,message,backMessage);
        serviceTask.execute();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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
