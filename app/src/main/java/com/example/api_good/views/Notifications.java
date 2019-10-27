package com.example.api_good.views;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.api_good.Events.Events_Respons;
import com.example.api_good.Live.Live_Response_live;
import com.example.api_good.R;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Notifications extends AppCompatActivity {
    SwitchCompat allevents,match_start,half_time,match_result,goal,yellow_card,red_card,yellowred_card,penalty_missed;
    private NotificationManagerCompat notificationManagerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_lay);
        allevents=(SwitchCompat)findViewById(R.id.all_events);
        match_start=(SwitchCompat)findViewById(R.id.start_match);
        half_time=(SwitchCompat)findViewById(R.id.half_time);
        match_result=(SwitchCompat)findViewById(R.id.result_match);
        goal=(SwitchCompat)findViewById(R.id.goal_goal);
        yellow_card=(SwitchCompat)findViewById(R.id.card_yellow);
        red_card=(SwitchCompat)findViewById(R.id.card_Red);
        yellowred_card=(SwitchCompat)findViewById(R.id.yellow_red_card);
        penalty_missed=(SwitchCompat)findViewById(R.id.penalty_missed);

        notificationManagerCompat=NotificationManagerCompat.from(this);


        SharedPreferences prefs = getSharedPreferences("com.example.srushtee.dummy", MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("service_status", false);

        if(switchState){
            //Do your work for switch is selected on
        } else {
            //Code for switch off
        }



                if (allevents.isChecked()) {


                    match_start.setChecked(true);
                    half_time.setChecked(true);
                    match_result.setChecked(true);
                    goal.setChecked(true);
                    red_card.setChecked(true);
                    yellowred_card.setChecked(true);
                    penalty_missed.setChecked(true);


                    SharedPreferences.Editor editor = getSharedPreferences("com.example.srushtee.dummy", MODE_PRIVATE).edit();
                    editor.putBoolean("active",true);
                    editor.apply();
                    editor.commit();


                    Intent serviceIntent=new Intent(Notifications.this,Exampleservice.class);
                    serviceIntent.putExtra("inputExtra","Start service");
                    startService(serviceIntent);

                }else {

                    SharedPreferences.Editor editor = getSharedPreferences("com.example.srushtee.dummy", MODE_PRIVATE).edit();
                    editor.putBoolean("stop",false);
                    editor.apply();
                    editor.commit();
                }
       /* int leagueID;

        int[] arr={1,132,137,136,194,195,524,94,198,3,759,4,436,8,10,87,11,18,34,114,135,492,294,6,767,150
                ,86,240,138,126,99,419,116,404,115,463,644,657,413};
        for (int i1 = 0; i1 < arr.length; i1++) {
            int i = arr[i1];

            leagueID = i;

            RetrofitClient.getInstance().getLive_events(leagueID, new HandleResponse() {
                @Override
                public void ResponseOK(JsonObject mainObject) {
                    Live_Response_live live_response_live = new Gson().fromJson(mainObject,Live_Response_live .class);
                    for (int i=0;i<live_response_live.getApi().getFixtures().size();i++){
                        int fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();
                        event_notify(fixture_id);

                    }

                }

                @Override
                public void ResponseError(String errorMessage) {

                }
            });

        }
    }



    public void event_notify(int fixture_id){

        RetrofitClient.getInstance().get_Events(fixture_id, new HandleResponse() {
            @Override
            public void ResponseOK(JsonObject mainObject) {
                Events_Respons events_respons=new Gson().fromJson(mainObject,Events_Respons.class);
                for (int x=0;x<events_respons.getApi().getEvents().size();x++){
                    String Type= events_respons.getApi().getEvents().get(x).getType();
                    String teamname=events_respons.getApi().getEvents().get(x).getTeamName();
                    String player_name=events_respons.getApi().getEvents().get(x).getPlayer();
                    String details=events_respons.getApi().getEvents().get(x).getDetail();



                    if (goal.isChecked()){
                        goal.setChecked(true);
                    if (Type.equals("Goal")){
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                        Notification notification=new NotificationCompat.Builder(Notifications.this,App.CHANNEL_2_ID)
                                .setSmallIcon(R.drawable.goal)
                                .setContentTitle("Gooooal....!")
                                .setContentText("["+teamname+"]").setContentText("("+player_name+")")
                                .setPriority(NotificationCompat.PRIORITY_LOW)
                                .setLights(Color.BLUE, 500, 500)
                                .setSound(alarmSound)
                                .build();
                        notificationManagerCompat.notify(2,notification);
                    }}

                    if(yellow_card.isChecked()){
                        yellow_card.setChecked(true);

                    if (Type.equals("Card")&& details.equals("Yellow Card")){
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                        Notification notification=new NotificationCompat.Builder(Notifications.this,App.CHANNEL_2_ID)
                                .setSmallIcon(R.drawable.yellowcard)
                                .setContentTitle("Yellow Card")
                                .setContentText("("+player_name+")").setContentText("["+teamname+"]")
                                .setPriority(NotificationCompat.PRIORITY_LOW)
                                .setLights(Color.BLUE, 500, 500)
                                .setSound(alarmSound)
                                .build();
                        notificationManagerCompat.notify(2,notification);
                    }}
                    if (red_card.isChecked()){
                        red_card.setChecked(true);
                    if (Type.equals("Card")&& details.equals("Red Card")){

                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                        Notification notification=new NotificationCompat.Builder(Notifications.this,App.CHANNEL_2_ID)
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
            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });*/
    }

    public void startService(View v){
        Intent serviceIntent=new Intent(this,Exampleservice.class);
        serviceIntent.putExtra("inputExtra","checked");
        startService(serviceIntent);
    }

    public void stopService(View v){
        Intent serviceIntent=new Intent(this,Exampleservice.class);
        startService(serviceIntent);
    }
}
