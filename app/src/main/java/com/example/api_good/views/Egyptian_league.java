package com.example.api_good.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.api_good.Leagues_Adapters.Egypt_PL_adapter;
import com.example.api_good.R;
import com.example.api_good.matches_model.Live_Response;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Egyptian_league extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_leagues);
        SendResponse();

    }
    public void SendResponse(){

        String leaguesID = getIntent().getExtras().get("leagues_id").toString();
        RetrofitClient.getInstance().getfixture_league(Integer.parseInt(leaguesID),"2019-08-09",new HandleResponse() {
            @Override
            public void ResponseOK(JsonObject mainObject) {
                Live_Response live_response=new Gson().fromJson(mainObject,Live_Response.class);

                recyclerView=(RecyclerView)findViewById(R.id.Rec_leagues);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Egyptian_league.this));
                adapter=new Egypt_PL_adapter(live_response.getApi().getFixtures(),Egyptian_league.this);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });

    }
}
