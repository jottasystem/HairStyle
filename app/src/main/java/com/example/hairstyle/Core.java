package com.example.hairstyle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Core extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        RecyclerView recyclerView = findViewById(R.id.card_list);

        List<cardItem> mlist = new ArrayList<>();

        mlist.add(new cardItem("Cabelo",R.drawable.cuthair));
        mlist.add(new cardItem("Maquiagem",R.drawable.makeup));
        mlist.add(new cardItem("Mãos e pés",R.drawable.hands));
        mlist.add(new cardItem("Depilação",R.drawable.depilation));
        mlist.add(new cardItem("Sobrancelhas",R.drawable.eyesbrown));
        mlist.add(new cardItem("Dia de noiva",R.drawable.daymaried));
        mlist.add(new cardItem("Massagens",R.drawable.relax));
        Adapter adapter = new Adapter(this,mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }
}
