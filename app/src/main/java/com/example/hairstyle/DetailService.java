package com.example.hairstyle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailService extends AppCompatActivity {
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_service);


        listView = (ListView) findViewById(R.id.id_list_price_service);
        final ArrayList<String> arrayList  = new ArrayList<>();

        arrayList.add("VALOOOR AQUII ");

        arrayList.add("Precco ");

        arrayList.add("valor de 123  ");

        arrayList.add("outros valores AQUII ");
        arrayList.add("VALOOOR mais  ");
        arrayList.add("vem da API AQUII ");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.activity_detail_service,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailService.this,"Clickei em " + position + " "+ arrayList.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}