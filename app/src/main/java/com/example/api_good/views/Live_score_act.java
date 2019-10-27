package com.example.api_good.views;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.api_good.Leagues_Adapters.adpt_live_score;
import com.example.api_good.Live.Live_Response_live;
import com.example.api_good.R;
import com.example.api_good.list_items.list_item_score;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class Live_score_act extends AppCompatActivity {

    RecyclerView recyclerView;
    List<list_item_score> list_item_scores;
    private RecyclerView.Adapter adapter;
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclev);
        status=(TextView)findViewById(R.id.full_Time);


        sendRequestToServer();


    }

    public void sendRequestToServer(){
        // get team by id
        //all teams
       /* RetrofitClient.getInstance().getLive_events(new HandleResponse() {
            @SuppressLint("NewApi")
            @Override
            public void ResponseOK(JsonObject mainObject) {

              Live_Response_live live_responseLive = new Gson().fromJson(mainObject, Live_Response_live.class);


                recyclerView = (RecyclerView) findViewById(R.id.recyclev);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Live_score_act.this));
                adapter = new adpt_live_score(live_responseLive.getApi().getFixtures(), Live_score_act.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });
*/
    }
}
