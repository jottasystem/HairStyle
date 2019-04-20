package com.example.hairstyle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Avaliation extends AppCompatActivity {

    private static final String URL_DATA = "https://simplifiedcoding.net/demos/marvel/";


    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<ListItemComments> getObjListItemComments;
    private RecyclerView getRecyclerView;



    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<ListItemComments> objListItemComments;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliation);


        recyclerView = (RecyclerView) findViewById(R.id.recycle_list_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Avaliação");



        objListItemComments = new ArrayList<>();

        /*
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
        */


    }




}
