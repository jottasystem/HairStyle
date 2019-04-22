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
import android.widget.RatingBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Avaliation extends AppCompatActivity {


    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private List<ListItemComments> objListItemComments;
    private RequestQueue mRequestQueue;
    private EditText input_comments;
    private Button buttonCommit;
    private RatingBar numberStart;
    public String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliation);

        Intent dados = getIntent();
        Bundle data = dados.getExtras();

        if (data != null) {
            String tryName = dados.getStringExtra("username");
            System.out.println("OIIIIAAA"+tryName);
        } else {
            userName = "Impacta";

        }

        recyclerView = (RecyclerView) findViewById(R.id.recycle_list_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        input_comments  = findViewById(R.id.id_input_comments);

        numberStart = findViewById(R.id.id_rating_start_comments);
        buttonCommit = findViewById(R.id.id_button_comit_comments);
        buttonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPost(userName,input_comments.getText().toString());
                input_comments.getText().clear();

            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Avaliação");


        objListItemComments = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    public void getPost (String name,String comments){
        String rate = "2";
        String endPoint = "https://demoope.herokuapp.com/api/comentarios";
        String message = "Comentando ...";
        String backMessage = "Comentário feito com sucesso !";

        ServiceTaskComments serviceTask = new ServiceTaskComments(this,endPoint,name,comments,rate,message,backMessage);
        serviceTask.execute();
        parseJSON();
    }

    private void parseJSON(){
        String url = "https://demoope.herokuapp.com/api/comentarios";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject comments = jsonArray.getJSONObject(i);

                                String creatorName = comments.getString("name");
                                String commentsPost = comments.getString("coment");

                                int ratingStar = comments.getInt("rate");

                                ListItemComments listComment = new ListItemComments(
                                       creatorName,
                                       commentsPost,
                                       ratingStar
                                );


                                objListItemComments.add(listComment);
                                adapter = new CommentsAdapter(objListItemComments,Avaliation.this);
                                recyclerView.setAdapter(adapter);

                                input_comments.getText().clear();

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
        mRequestQueue.add(request);

    }


}

