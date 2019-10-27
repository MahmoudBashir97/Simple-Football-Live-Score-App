package com.example.api_good.views;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_good.Models.TeamsResponse;
import com.example.api_good.Models.teams;
import com.example.api_good.R;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView txt,team_slg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // txt=(TextView)findViewById(R.id.txt);
        //team_slg=(TextView)findViewById(R.id.team_slug);
        img=(ImageView)findViewById(R.id.img);

        sendRequestToServer();

    }
    public void sendRequestToServer(){
        // get team by id
        RetrofitClient.getInstance().getTeamByID("33", new HandleResponse() {
            @Override
            public void ResponseOK(JsonObject mainObject) {
             /* JsonObject data=  mainObject.get("api").getAsJsonObject();
                JsonArray teams = data.get("teams").getAsJsonArray();
                for (int i = 0; i < teams.size() ; i++) {
                    JsonObject team = teams.get(i).getAsJsonObject();
                    team.get("name").getAsString();
                }*/

             TeamsResponse teamsResponse = new Gson().fromJson(mainObject,TeamsResponse.class);
             String name= teamsResponse.getApi().getTeams().get(0).getName();
                Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                txt.setText(name);
                String urlimage=teamsResponse.getApi().getTeams().get(0).getLogo();
                img.setImageURI(Uri.parse(urlimage));



            }


            @Override
            public void ResponseError(String errorMessage) {

            }
        });

        // get all players
        RetrofitClient.getInstance().getAllPlayer(new HandleResponse() {
            @Override
            public void ResponseOK(JsonObject mainObject) {



            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });
        //all teams
        RetrofitClient.getInstance().getAllTeams(new HandleResponse() {
            @Override
            public void ResponseOK(JsonObject mainObject) {


            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });

}}
