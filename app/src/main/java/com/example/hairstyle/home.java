package com.example.hairstyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView userLogin;
    public String nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //Listagem inicial
        RecyclerView recyclerView = findViewById(R.id.card_list);
        List<cardItem> mlist = new ArrayList<>();
        mlist.add(new cardItem("Cabelo", R.drawable.cuthair));
        mlist.add(new cardItem("Maquiagem", R.drawable.makeup));
        mlist.add(new cardItem("Mãos e pés", R.drawable.hands));
        mlist.add(new cardItem("Depilação", R.drawable.depilation));
        mlist.add(new cardItem("Sobrancelhas", R.drawable.eyesbrown));
        mlist.add(new cardItem("Dia de noiva", R.drawable.daymaried));
        mlist.add(new cardItem("Massagens", R.drawable.relax));
        Adapter adapter = new Adapter(this, mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Mudar nome
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");

        //Get data passed the last Activity

        Intent dados = getIntent();
        Bundle data = dados.getExtras();
        TextView userLogin = (TextView) findViewById(R.id.id_user_name_header);

        if (data != null) {
            String tryName = dados.getStringExtra("username");
            System.out.println("OIIIII"+tryName);
        } else {
            nameUser = "Impacta";

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.detailCompany) {

            Intent detail = new Intent(home .this,DetailCompany.class);
            startActivity(detail);

        System.out.println(("CLIQUEEE"));

        } else if (id == R.id.my_service) {
            Intent my_service = new Intent(home .this, DetailService.class);
            startActivity(my_service);

        } else if (id == R.id.myAccount) {
            System.out.println(("CLIQUEEE"));

        } else if (id == R.id.myAgend) {
            System.out.println(("CLIQUEEE AGENDA"));

        } else if (id == R.id.nav_share) {
            System.out.println(("CLIQUEEE"));

        } else if (id == R.id.avaliation) {

            Intent myAvaliation = new Intent(home .this, Avaliation.class);
            Bundle data = new Bundle();
            data.putString("username", nameUser);

            myAvaliation.putExtras(data);
            startActivity(myAvaliation);


        }else if (id == R.id.idExitApp) {

            Intent exitApp = new Intent(home .this,MainActivity.class);
            startActivity(exitApp);

        }else if (id == R.id.contact) {
            Intent contact = new Intent(home .this,Contact.class);
            startActivity(contact);
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
