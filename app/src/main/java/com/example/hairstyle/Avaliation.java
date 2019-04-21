package com.example.hairstyle;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Avaliation extends AppCompatActivity {


    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<ListItemComments> objListItemComments;

    private EditText input_comments;
    private Button buttonCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliation);


        recyclerView = (RecyclerView) findViewById(R.id.recycle_list_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        input_comments  = findViewById(R.id.id_input_comments);

        buttonCommit = findViewById(R.id.id_button_comit_comments);

        buttonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPost();
                input_comments.getText().clear();

            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Avaliação");


        objListItemComments = new ArrayList<>();

        for( int i = 0 ; i <=10 ; i++) {
            ListItemComments listComment = new ListItemComments(
                    "Nome de quem comentou " + (i + 1),
                    "Comentario" + (i + 1),
                    3
            );

            //AQUI VEM O OBJETO DE COMENTARIOS DENTRO DO LIST COMMENTS
            objListItemComments.add(listComment);
        }

        adapter = new CommentsAdapter(objListItemComments,this);
        recyclerView.setAdapter(adapter);


    }

    public void getPost (){

        String name = "Jotta";
        String comments = input_comments.getText().toString();
        String rate = "5";
        String endPoint = "https://demoope.herokuapp.com/api/comentarios";
        String message = "Comentando ...";
        String backMessage = "Comentário feito com sucesso !";
        ServiceTask serviceTask = new ServiceTask(this,endPoint,name,comments,rate,message,backMessage);
        serviceTask.execute();

    }


}
