package com.example.api_good.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.api_good.Leagues_Adapters.Egypt_PL_adapter;
import com.example.api_good.Leagues_Adapters.adpt_order;
import com.example.api_good.Order.Order_Response;
import com.example.api_good.Order.standings;
import com.example.api_good.R;
import com.example.api_good.fixture_league.Live_Response;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ImageButton imageButton;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        imageButton=(ImageButton)findViewById(R.id.img_bu);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(News.this,Act_with_navigation.class);
                startActivity(intent);
            }
        });



    }
}
