package com.example.api_good.views;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.api_good.Events.Events_Respons;
import com.example.api_good.Leagues_Adapters.adpt_Big;

import com.example.api_good.Leagues_Adapters.adpt_live_events;
import com.example.api_good.Live.Live_Response_live;
import com.example.api_good.R;
import com.example.api_good.fixture_league.Live_Response;

import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

public class Act_with_navigation extends AppCompatActivity
                       implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView England_rec,Seria_A,Bundesliga,france_league,Spain_league,Champions_league,Europa_league,Portogal_league,Natherland_league,Egyptian_leag,Pro_league,
            Emirates_league,morocco_league,Tunisia_league,Libanon_league;
    private RecyclerView.Adapter adapter;

    LinearLayout ln_England,ln_Seria_A,ln_france_leag,ln_Spain_leag,ln_Bundesliga,ln_Champions_leag,ln_Europa_leag,ln_Natherland_leag
            ,ln_Portogal_leag,ln_Egyptian_leag,ln_Pro_leag,ln_Emirates_leag,ln_Morocco_leag,ln_Tunisian_leag,ln_Lebanon_leag;

    ImageView imgv1,imgv2;
    SwitchCompat allevents;
    private NotificationManagerCompat notificationManagerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);


        ln_England=(LinearLayout)findViewById(R.id.ln_England_leag);
        ln_Seria_A=(LinearLayout)findViewById(R.id.ln_Seria_A_leag);
        ln_france_leag=(LinearLayout)findViewById(R.id.ln_league_france_leag);
        ln_Spain_leag=(LinearLayout)findViewById(R.id.ln_Spain_leag);
        ln_Bundesliga=(LinearLayout)findViewById(R.id.ln_Bundeliga);
        ln_Champions_leag=(LinearLayout)findViewById(R.id.ln_champions_leag);
        ln_Europa_leag=(LinearLayout)findViewById(R.id.ln_Europa_leag);
        ln_Natherland_leag=(LinearLayout)findViewById(R.id.ln_Natherland_leag);
        ln_Portogal_leag=(LinearLayout)findViewById(R.id.ln_Portogal_leag);
        ln_Egyptian_leag=(LinearLayout)findViewById(R.id.ln_Egyptian_leag);
        ln_Pro_leag=(LinearLayout)findViewById(R.id.ln_Pro_leag);
        ln_Emirates_leag=(LinearLayout)findViewById(R.id.ln_Emirates_leag);
        ln_Morocco_leag=(LinearLayout)findViewById(R.id.ln_Moroccoleag);
        ln_Tunisian_leag=(LinearLayout)findViewById(R.id.ln_Tunisia_leag);
        ln_Lebanon_leag=(LinearLayout)findViewById(R.id.ln_Libanon_leag);

        // init views

        initviews_England();
        initviews_Seria_A();
        initviews_Spain_league();
        initviews_Bundesliga();
        initviews_France();
        initviews_Champions_league();
        initviews_Europa_league();
        initviews_Portogal_league();
        initviews_Natherland_league();
        initViews_Egyptian();
        initviews_Pro_league();
        initviews_Emirates_league();
        initviews_morocco_league();
        initviews_Tunisia_league();
        initviews_Libanon_league();





        notificationManagerCompat=NotificationManagerCompat.from(this);


        calendar();
       // sendRequestToServer();


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {

        int id=Item.getItemId();

        Intent intent;
        switch (Item.getItemId()){

            case R.id.news:
                intent=new Intent(Act_with_navigation.this, News.class);
                startActivity(intent);

                break;
            case R.id.matches:
                intent=new Intent(Act_with_navigation.this, Matches.class);
                startActivity(intent);

                break;
            case R.id.competition:
                intent=new Intent(Act_with_navigation.this, Competition.class);
                startActivity(intent);

                break;
            case R.id.fav:
                intent=new Intent(Act_with_navigation.this, Favourites.class);
                startActivity(intent);

                break;
            case R.id.settings:
                intent=new Intent(Act_with_navigation.this, Settings.class);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }



    public void calendar(){
        /* starts before 1 month from now */
        java.util.Calendar startDate = java.util.Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        java.util.Calendar endDate = java.util.Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(5)
                .monthFormat("MMM")
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {



            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSelected(Date date, int position) {

                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                String cDate= cdate.format(new Date());
                final String   selected_date=cdate.format(date);

                if (cDate.equals(selected_date)){

                    RetrofitClient.getInstance().getLive_events(524, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject, Live_Response_live.class);
                            for (int i = 0; i < live_response_live.getApi().getFixtures().size(); i++) {

                                String event_date = live_response_live.getApi().getFixtures().get(i).getEvent_date();

                                String[] separated = event_date.split("T");
                                String tx = separated[0];
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);

                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(),Act_with_navigation.this);
                                England_rec.setAdapter(ab);

                                ln_England.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_England.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(891, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);

                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Seria_A.setAdapter(ab);

                                ln_Seria_A.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Seria_A.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(525, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                france_league.setAdapter(ab);


                                ln_france_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_france_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(754, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                               // event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Bundesliga.setAdapter(ab);


                                ln_Bundesliga.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Bundesliga.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(775, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                               // event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Spain_league.setAdapter(ab);


                                ln_Spain_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Spain_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(530, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Champions_league.setAdapter(ab);


                                ln_Champions_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Champions_leag.setVisibility(View.GONE);
                        }
                    });

                    RetrofitClient.getInstance().getLive_events(514, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                               // event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Europa_league.setAdapter(ab);


                                ln_Europa_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Europa_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(566, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                               // event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Natherland_league.setAdapter(ab);


                                ln_Natherland_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Natherland_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(766, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                               // event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Portogal_league.setAdapter(ab);


                                ln_Portogal_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Portogal_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(126, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Egyptian_leag.setAdapter(ab);


                                ln_Egyptian_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Egyptian_leag.setVisibility(View.GONE);
                        }
                    });


                    RetrofitClient.getInstance().getLive_events(901, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Pro_league.setAdapter(ab);

                                ln_Pro_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Pro_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(789, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Emirates_league.setAdapter(ab);

                                ln_Emirates_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Emirates_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(115, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                               // event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                morocco_league.setAdapter(ab);

                                ln_Morocco_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Morocco_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(897, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                               // event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Tunisia_league.setAdapter(ab);


                                ln_Tunisian_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Tunisian_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getLive_events(657, new HandleResponse() {
                        @Override
                        public void ResponseOK(JsonObject mainObject) {
                            Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                            for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){

                                String event_date=live_response_live.getApi().getFixtures().get(i).getEvent_date();
                                String[] separated=event_date.split("T");
                                String tx=separated[0];
                                SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                                String   Current_date=cdate.format(new Date());
                                int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                                //event_notify(fixture_id);
                                adpt_live_events ab = new adpt_live_events(live_response_live.getApi().getFixtures(), Act_with_navigation.this);
                                Libanon_league.setAdapter(ab);


                                ln_Lebanon_leag.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void ResponseError(String errorMessage) {
                            ln_Lebanon_leag.setVisibility(View.GONE);
                        }
                    });
                    RetrofitClient.getInstance().getfixture_league(524,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();

                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            int fixture_id=live_response.getApi().getFixtures().get(i).getFixture_id();

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            England_rec.setAdapter(ab);

                            ln_England.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_England.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(891,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Seria_A.setAdapter(ab);

                            ln_Seria_A.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Seria_A.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(525,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            france_league.setAdapter(ab);


                            ln_france_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_france_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(754,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Bundesliga.setAdapter(ab);


                            ln_Bundesliga.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Bundesliga.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(775,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Spain_league.setAdapter(ab);



                            ln_Spain_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Spain_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(530,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Champions_league.setAdapter(ab);


                            ln_Champions_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Champions_leag.setVisibility(View.GONE);
                    }
                });

                RetrofitClient.getInstance().getfixture_league(514,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Europa_league.setAdapter(ab);


                            ln_Europa_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Europa_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(566,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Natherland_league.setAdapter(ab);


                            ln_Natherland_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Natherland_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(766,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Portogal_league.setAdapter(ab);


                            ln_Portogal_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Portogal_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(126,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Egyptian_leag.setAdapter(ab);


                            ln_Egyptian_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Egyptian_leag.setVisibility(View.GONE);
                    }
                });


                RetrofitClient.getInstance().getfixture_league(901,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                                adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                                Pro_league.setAdapter(ab);

                            ln_Pro_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Pro_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(897,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Emirates_league.setAdapter(ab);

                            ln_Emirates_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Emirates_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(897,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Tunisia_league.setAdapter(ab);


                            ln_Tunisian_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Tunisian_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(657,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Libanon_league.setAdapter(ab);


                            ln_Lebanon_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Lebanon_leag.setVisibility(View.GONE);
                    }
                });

            }else{
                RetrofitClient.getInstance().getfixture_league(524,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();

                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            int fixture_id=live_response.getApi().getFixtures().get(i).getFixture_id();

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            England_rec.setAdapter(ab);

                            ln_England.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_England.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(891,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Seria_A.setAdapter(ab);

                            ln_Seria_A.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Seria_A.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(525,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            france_league.setAdapter(ab);


                            ln_france_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_france_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(754,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Bundesliga.setAdapter(ab);


                            ln_Bundesliga.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Bundesliga.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(775,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Spain_league.setAdapter(ab);



                            ln_Spain_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Spain_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(530,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Champions_league.setAdapter(ab);


                            ln_Champions_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Champions_leag.setVisibility(View.GONE);
                    }
                });

                RetrofitClient.getInstance().getfixture_league(514,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Europa_league.setAdapter(ab);


                            ln_Europa_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Europa_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(566,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Natherland_league.setAdapter(ab);


                            ln_Natherland_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Natherland_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(766,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Portogal_league.setAdapter(ab);


                            ln_Portogal_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Portogal_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(126,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Egyptian_leag.setAdapter(ab);


                            ln_Egyptian_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Egyptian_leag.setVisibility(View.GONE);
                    }
                });


                RetrofitClient.getInstance().getfixture_league(901,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Pro_league.setAdapter(ab);

                            ln_Pro_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Pro_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(789,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Emirates_league.setAdapter(ab);

                            ln_Emirates_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Emirates_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(897,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Tunisia_league.setAdapter(ab);


                            ln_Tunisian_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Tunisian_leag.setVisibility(View.GONE);
                    }
                });
                RetrofitClient.getInstance().getfixture_league(657,selected_date, new HandleResponse() {
                    @Override
                    public void ResponseOK(JsonObject mainObject) {
                        Live_Response live_response = new Gson().fromJson(mainObject, Live_Response.class);
                        for (int i=0;i<live_response.getApi().getFixtures().size();i++){

                            String event_date=live_response.getApi().getFixtures().get(i).getEvent_date();
                            Toast.makeText(Act_with_navigation.this, ""+event_date, Toast.LENGTH_LONG).show();
                            String[] separated=event_date.split("T");
                            String tx=separated[0];
                            SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
                            String   Current_date=cdate.format(new Date());

                            adpt_Big ab = new adpt_Big(live_response.getApi().getFixtures(), Act_with_navigation.this);
                            Libanon_league.setAdapter(ab);


                            ln_Lebanon_leag.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void ResponseError(String errorMessage) {
                        ln_Lebanon_leag.setVisibility(View.GONE);
                    }
                });
                }
            }
        });
    }

       /* RetrofitClient.getInstance().getLive_events(new HandleResponse() {
            @SuppressLint("NewApi")
            @Override
            public void ResponseOK(JsonObject mainObject) {

                Live_Response_live live_response = new Gson().fromJson(mainObject, Live_Response_live.class);


                recyclerView = (RecyclerView) findViewById(R.id.Big_rec);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
                adapter = new adpt_live_score(live_response.getApi().getFixtures(), Act_with_navigation.this);
                recyclerView.setAdapter(adapter);



                String T_name=live_response.getApi().getFixtures().get(0).getHomeTeam().getTeam_name();
                if (T_name.equals("Egypt")){
                    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                    Notification notification=new NotificationCompat.Builder(Act_with_navigation.this,App.CHANNEL_2_ID)
                            .setSmallIcon(R.drawable.goal)
                            .setContentTitle("Gooooal....!")
                            .setContentText("Egypt")
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setLights(Color.BLUE, 500, 500)
                            .setSound(alarmSound)
                            .build();
                    notificationManagerCompat.notify(2,notification);
                }

                Toast.makeText(Act_with_navigation.this, "date :" + live_response.getApi().getFixtures().get(0).getEvent_date() , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });*/
       private void initviews_England() {
           England_rec = (RecyclerView) findViewById(R.id.England_PL);
           England_rec.setHasFixedSize(true);
           England_rec.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
           England_rec.setNestedScrollingEnabled(true);
       }
    private void initviews_Seria_A() {
        Seria_A = (RecyclerView) findViewById(R.id.Seria_A);
        Seria_A.setHasFixedSize(true);
        Seria_A.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Seria_A.setNestedScrollingEnabled(true);
    }
    private void initviews_Bundesliga() {
        Bundesliga = (RecyclerView) findViewById(R.id.Bundesliga);
        Bundesliga.setHasFixedSize(true);
        Bundesliga.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Bundesliga.setNestedScrollingEnabled(true);
    }
    private void initviews_France() {
        france_league = (RecyclerView) findViewById(R.id.league_france);
        france_league.setHasFixedSize(true);
        france_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        france_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Spain_league() {
        Spain_league = (RecyclerView) findViewById(R.id.Spain_league);
        Spain_league.setHasFixedSize(true);
        Spain_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Spain_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Champions_league() {
        Champions_league = (RecyclerView) findViewById(R.id.Champions_league);
        Champions_league.setHasFixedSize(true);
        Champions_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Champions_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Europa_league() {
        Europa_league = (RecyclerView) findViewById(R.id.Europa_league);
        Europa_league.setHasFixedSize(true);
        Europa_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Europa_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Portogal_league() {
        Portogal_league = (RecyclerView) findViewById(R.id.Portogal_league);
        Portogal_league.setHasFixedSize(true);
        Portogal_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Portogal_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Natherland_league() {

        Natherland_league = (RecyclerView) findViewById(R.id.Natherland_league);
        Natherland_league.setHasFixedSize(true);
        Natherland_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Natherland_league.setNestedScrollingEnabled(true);
    }
    private void initViews_Egyptian() {
        Egyptian_leag = (RecyclerView) findViewById(R.id.Egyptian_leag);
        Egyptian_leag.setHasFixedSize(true);
        Egyptian_leag.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Egyptian_leag.setNestedScrollingEnabled(true);
    }
    private void initviews_Pro_league() {
        Pro_league = (RecyclerView) findViewById(R.id.Pro_league);
        Pro_league.setHasFixedSize(true);
        Pro_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Pro_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Emirates_league() {
        Emirates_league = (RecyclerView) findViewById(R.id.Emirates_league);
        Emirates_league.setHasFixedSize(true);
        Emirates_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Emirates_league.setNestedScrollingEnabled(true);
    }
    private void initviews_morocco_league() {
        morocco_league = (RecyclerView) findViewById(R.id.morocco_league);
        morocco_league.setHasFixedSize(true);
        morocco_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        morocco_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Tunisia_league() {
        Tunisia_league = (RecyclerView) findViewById(R.id.Tunisia_league);
        Tunisia_league.setHasFixedSize(true);
        Tunisia_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Tunisia_league.setNestedScrollingEnabled(true);
    }
    private void initviews_Libanon_league() {
        Libanon_league = (RecyclerView) findViewById(R.id.Libanon_league);
        Libanon_league.setHasFixedSize(true);
        Libanon_league.setLayoutManager(new LinearLayoutManager(Act_with_navigation.this));
        Libanon_league.setNestedScrollingEnabled(true);
    }


   /* public void event_notify(int fixture_id){

           RetrofitClient.getInstance().get_Events(fixture_id, new HandleResponse() {
               @Override
               public void ResponseOK(JsonObject mainObject) {
                   Events_Respons events_respons=new Gson().fromJson(mainObject,Events_Respons.class);
                   for (int x=0;x<events_respons.getApi().getEvents().size();x++){
                      String Type= events_respons.getApi().getEvents().get(x).getType();
                      String teamname=events_respons.getApi().getEvents().get(x).getTeamName();
                      String player_name=events_respons.getApi().getEvents().get(x).getPlayer();
                      String details=events_respons.getApi().getEvents().get(x).getDetail();
                      if (Type.equals("Goal")){
                          Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                          Notification notification=new NotificationCompat.Builder(Act_with_navigation.this,App.CHANNEL_2_ID)
                                  .setSmallIcon(R.drawable.goal)
                                  .setContentTitle("Gooooal....!")
                                  .setContentText("["+teamname+"]").setContentText("("+player_name+")")
                                  .setPriority(NotificationCompat.PRIORITY_LOW)
                                  .setLights(Color.BLUE, 500, 500)
                                  .setSound(alarmSound)
                                  .build();
                          notificationManagerCompat.notify(2,notification);
                      }
                      if (Type.equals("Card")&& details.equals("Yellow Card")){
                          Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                          Notification notification=new NotificationCompat.Builder(Act_with_navigation.this,App.CHANNEL_2_ID)
                                  .setSmallIcon(R.drawable.yellowcard)
                                  .setContentTitle("Yellow Card")
                                  .setContentText("("+player_name+")").setContentText("["+teamname+"]")
                                  .setPriority(NotificationCompat.PRIORITY_LOW)
                                  .setLights(Color.BLUE, 500, 500)
                                  .setSound(alarmSound)
                                  .build();
                          notificationManagerCompat.notify(2,notification);
                      }

                      if (Type.equals("Card")&& details.equals("Red Card")){

                          Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                          Notification notification=new NotificationCompat.Builder(Act_with_navigation.this,App.CHANNEL_2_ID)
                                  .setSmallIcon(R.drawable.yellowcard)
                                  .setContentTitle("Yellow Card")
                                  .setContentText("("+player_name+")").setContentText("["+teamname+"]")
                                  .setPriority(NotificationCompat.PRIORITY_LOW)
                                  .setLights(Color.BLUE, 500, 500)
                                  .setSound(alarmSound)
                                  .build();
                          notificationManagerCompat.notify(2,notification);
                      }
                   }
               }

               @Override
               public void ResponseError(String errorMessage) {

               }
           });

       }*/
}





