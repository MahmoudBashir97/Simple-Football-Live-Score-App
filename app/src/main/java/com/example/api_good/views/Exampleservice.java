package com.example.api_good.views;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

public class Exampleservice extends Service {

    SwitchCompat allevents,match_start,half_time,match_result,goal,yellow_card,red_card,yellowred_card,penalty_missed;
    private NotificationManagerCompat notificationManagerCompat;
    int fixture_id;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String input=intent.getStringExtra("inputExtra");
        Intent notificationIntent=new Intent(this,Notifications.class);

        final PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);

        int leagueID;
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
                        fixture_id = live_response_live.getApi().getFixtures().get(i).getFixture_id();

                        Toast.makeText(Exampleservice.this, ""+fixture_id, Toast.LENGTH_SHORT).show();
                        //  event_notify(fixture_id);

                    }

                }

                @Override
                public void ResponseError(String errorMessage) {

                }
            });

        }
/*
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

                            Notification notification1=new NotificationCompat.Builder(Exampleservice.this,App.CHANNEL_2_ID)
                                    .setSmallIcon(R.drawable.goal)
                                    .setContentTitle("Gooooal....!")
                                    .setContentText("["+teamname+"]").setContentText("("+player_name+")")
                                    .setContentIntent(pendingIntent)
                                    .setPriority(NotificationCompat.PRIORITY_LOW)
                                    .setLights(Color.BLUE, 500, 500)
                                    .setSound(alarmSound)
                                    .build();
                            notificationManagerCompat.notify(2,notification1);
                            startForeground(1,notification1);
                        }}

                    if(yellow_card.isChecked()){
                        yellow_card.setChecked(true);

                        if (Type.equals("Card")&& details.equals("Yellow Card")){
                            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                            Notification notification=new NotificationCompat.Builder(Exampleservice.this,App.CHANNEL_2_ID)
                                    .setSmallIcon(R.drawable.yellowcard)
                                    .setContentTitle("Yellow Card")
                                    .setContentText("("+player_name+")").setContentText("["+teamname+"]")
                                    .setContentIntent(pendingIntent)
                                    .setPriority(NotificationCompat.PRIORITY_LOW)
                                    .setLights(Color.BLUE, 500, 500)
                                    .setSound(alarmSound)
                                    .build();
                            notificationManagerCompat.notify(2,notification);
                            startForeground(1,notification);
                        }}
                    if (red_card.isChecked()){
                        red_card.setChecked(true);
                        if (Type.equals("Card")&& details.equals("Red Card")){

                            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                            Notification notification=new NotificationCompat.Builder(Exampleservice.this,App.CHANNEL_2_ID)
                                    .setSmallIcon(R.drawable.yellowcard)
                                    .setContentTitle("Yellow Card")
                                    .setContentText("("+player_name+")").setContentText("["+teamname+"]")
                                    .setContentIntent(pendingIntent)
                                    .setPriority(NotificationCompat.PRIORITY_LOW)
                                    .setLights(Color.BLUE, 500, 500)
                                    .setSound(alarmSound)
                                    .build();
                            notificationManagerCompat.notify(2,notification);
                            startForeground(1,notification);
                        }
                    }
                }

                }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });*/
        //stopSelf();
        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
