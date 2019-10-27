package com.example.api_good.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.api_good.Leagues_Adapters.Competiton_Adapter;
import com.example.api_good.R;
import com.example.api_good.leagues.leagues;
import com.example.api_good.leagues.leagues_Response;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Competition extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<leagues> leaguesList=new ArrayList<leagues>();
    ImageButton imageButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        imageButton = (ImageButton) findViewById(R.id.img_bu);

        sendRequestToServer();

        //lst_data();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Competition.this, Act_with_navigation.class);
                startActivity(intent);

            }
        });
    }
      /*  public void lst_data(){
            int[] arr={2,3,759,94,198,4,436,8,10,87,11,18,34,114,135,492,294,6,767,150
                    ,86,240,138,126,99,419,116,404,115,463,644,657,413};
            int leagueID;
            recyclerView = (RecyclerView) findViewById(R.id.rec_competition);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(Competition.this));
            listitems_leagues=new ArrayList<>();
            List_item_leagues list;

            for (int i=0;i<arr.length;i++){

                leagueID=arr[i];
            RetrofitClient.getInstance().getleagues(leagueID, new HandleResponse() {
                @Override
                public void ResponseOK(JsonObject mainObject) {
                    leagues_Response leagues_response = new Gson().fromJson(mainObject, leagues_Response.class);

                    String comp_name ;
                    String url_img ;
                    listitems_leagues=new ArrayList<>();
                    List_item_leagues list;
                    for (int x=0;x<leagues_response.getApi().getLeagues().size();x++) {
                        switch (x){
                            case 0:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 1:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 2:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 3:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 4:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 5:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 6:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 7:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 8:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 9:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 10:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 11:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 12:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 13:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 14:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 15:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 16:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 17:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 18:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 19:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 20:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 21:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 22:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 23:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 24:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 25:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 26:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 27:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 28:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 29:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 30:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 31:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            case 32:
                                comp_name =leagues_response.getApi().getLeagues().get(x).getName();
                                url_img=leagues_response.getApi().getLeagues().get(x).getLogo();
                                list=new List_item_leagues(comp_name,url_img);
                                listitems_leagues.add(list);
                                break;
                            default:
                                list=new List_item_leagues("Error","Error");
                                listitems_leagues.add(list);
                        }
                    }
                    adapter=new Competiton_Adapter(listitems_leagues,Competition.this);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void ResponseError(String errorMessage) {

                }
            });
        }
    }*/

    //}

    public void sendRequestToServer(){
        int leagueID;

        int[] arr={1,132,137,136,194,195,524,94,198,3,759,4,436,8,10,87,11,18,34,114,135,492,294,6,767,150
                   ,86,240,138,126,99,419,116,404,115,463,644,657,413};
        for (int i1 = 0; i1 < arr.length; i1++) {
            int i = arr[i1];

            leagueID = i;
                    RetrofitClient.getInstance().getleagues(leagueID, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {

                            leagues_Response leagues_response = new Gson().fromJson(mainObject, leagues_Response.class);
                            leaguesList.add(leagues_response.getApi().getLeagues().get(0));
                        }

                        @Override
                        public void ResponseError(String errorMessage) {

                        }
                    });
        }
        recyclerView = (RecyclerView) findViewById(R.id.rec_competition);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Competition.this));
        adapter=new Competiton_Adapter(leaguesList,Competition.this);
        recyclerView.setAdapter(adapter);
       /* recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=recyclerView.getId();
                switch(id){

                    case 0:
                        Intent i=new Intent(Competition.this,Egyptian_league.class);
                        startActivity(i);
                    default:
                        Toast.makeText(Competition.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}
