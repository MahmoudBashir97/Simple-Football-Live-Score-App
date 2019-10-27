package com.example.api_good.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.api_good.Leagues_Adapters.adpt_Matches;
import com.example.api_good.R;
import com.example.api_good.fixture_league.Live_Response;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Matches extends AppCompatActivity {
    ImageButton imageButton;
    Intent intent;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    String League_ID;
    String Match_date;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        imageButton=(ImageButton)findViewById(R.id.img_bu);


        database = FirebaseDatabase.getInstance();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Matches.this,Act_with_navigation.class);
                startActivity(intent);
            }
        });
        get_data();
    }
    public void sendResponse(){
        SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
        String   Current_date=cdate.format(new Date());

        if (Current_date.equals(Match_date)){

            RetrofitClient.getInstance().getfixture_league(Integer.parseInt(League_ID),Current_date, new HandleResponse() {
                @Override
                public void ResponseOK(JsonObject mainObject) {

                    Live_Response live_response=new Gson().fromJson(mainObject,Live_Response.class);
                    recyclerView=(RecyclerView)findViewById(R.id.rec_mmatches);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Matches.this));
                    adapter=new adpt_Matches(Matches.this,live_response.getApi().getFixtures());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void ResponseError(String errorMessage) {

                }
            });
        }else {
            Toast.makeText(this, "Conflict Date - Sorry !!", Toast.LENGTH_SHORT).show();
        }

    }

    public void get_data(){
        myRef=database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );

                 League_ID=map.get("league_ID");
                 Match_date=map.get("match_date");

                 sendResponse();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
