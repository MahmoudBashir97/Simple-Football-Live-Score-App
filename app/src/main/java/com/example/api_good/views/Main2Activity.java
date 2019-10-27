package com.example.api_good.views;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.api_good.Leagues_Adapters.Myadapter;
import com.example.api_good.Models.TeamsResponse;
import com.example.api_good.R;
import com.example.api_good.list_items.listitem;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main2Activity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<listitem> listitems;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclev);

        sendRequestToServer();


    }






    public void sendRequestToServer(){
        // get team by id
        //all teams

        RetrofitClient.getInstance().getAllTeams(new HandleResponse() {
            @SuppressLint("NewApi")
            @Override
            public void ResponseOK(JsonObject mainObject) {

                imageView=(ImageView)findViewById(R.id.img);


                TeamsResponse teamsResponse = new Gson().fromJson(mainObject,TeamsResponse.class);

                recyclerView = (RecyclerView) findViewById(R.id.recyclev);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                listitems = new ArrayList<>();

                String name;
                listitem list;
                for (int i = 0; i <= teamsResponse.getApi().getTeams().size(); i++) {
                    switch (i) {
                        case 0:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 1:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 2:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 3:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 4:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 5:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 6:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 7:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 8:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 9:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 10:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 11:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 12:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 13:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 14:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 15:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 16:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 17:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 18:
                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        case 19:

                            name = teamsResponse.getApi().getTeams().get(i).getName();
                            list = new listitem(name);
                            listitems.add(list);
                            break;
                        default:
                            list = new listitem("لا توجد مباريات اليوم !");
                            listitems.add(list);
                    }

                }
                adapter = new Myadapter(listitems, Main2Activity.this);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });

    }
}
