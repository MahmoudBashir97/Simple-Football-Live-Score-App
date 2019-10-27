package com.example.api_good.Leagues_Adapters;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_good.R;


import com.example.api_good.matches_model.fixtures;
import com.example.api_good.views.Act_with_navigation;
import com.example.api_good.views.App;
import com.squareup.picasso.Picasso;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Egypt_PL_adapter  extends RecyclerView.Adapter<Egypt_PL_adapter.ViewHolder> {


    private List<fixtures> listitemLeagues;
    private Context context;
    private NotificationManagerCompat notificationManagerCompat;

    public Egypt_PL_adapter(List<fixtures> list_item_leagues, Context context){
        this.listitemLeagues=list_item_leagues;
        this.context=context;
       // notificationManagerCompat=NotificationManagerCompat.from(context);
    }
    @NonNull
    @Override
    public Egypt_PL_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.live_score,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        fixtures list_item_leagues=listitemLeagues.get(i);

        holder.t_home.setText(list_item_leagues.getHomeTeam().getTeam_name());
        holder.t_away.setText(list_item_leagues.getAwayTeam().getTeam_name());
        Picasso.with(context).load(list_item_leagues.getHomeTeam().getLogo()).resize(30,30).into(holder.logo_home);
        Picasso.with(context).load(list_item_leagues.getAwayTeam().getLogo()).resize(30,30).into(holder.logo_away);

        String spl=list_item_leagues.getEvent_date();
        String[] separated=spl.split("T");
        String tx=separated[0];
        String[] arr=separated[1].split(":00\\+");
        String tx1=arr[0];
        holder.match_date.setText(tx);


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
        String   Current_date=cdate.format(new Date());
        String[] Separated=Current_date.split("-");
        String   prt1=Separated[0];
        String[] Sep=Separated[1].split("-");
        String   prt2=Sep[0];
        String[] Sep2=Current_date.split("08-");
        String prt3=Sep2[1];

        Toast.makeText(context,prt1+"/"+prt2+"/"+prt3 , Toast.LENGTH_SHORT).show();
        long ms = list_item_leagues.getEvent_timestamp();
        long s = ms % 60;
        long m = (ms / 60) % 60;
        long h = (ms / (60 * 60)) % 24;

        String time_Stamp = String.format("%d:%02d", h, m, s);
        holder.time.setText(time_Stamp);

        Time time=new Time();
        time.setToNow();
        String match_time= String.valueOf(time.toMillis(false));
        holder.result.setText(" - ");

       /* if (match_time.equals(String.valueOf(list_item_leagues.getEvent_timestamp()))){

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification notification=new NotificationCompat.Builder(context, App.CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.strt_match)
                    .setContentTitle("Match start")
                    .setContentText(list_item_leagues.getHomeTeam() +" - "+list_item_leagues.getAwayTeam())
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setLights(Color.BLUE, 500, 500)
                    .setSound(alarmSound)
                    .build();
            notificationManagerCompat.notify(2,notification);
        }*/

       /* if (Integer.parseInt(tx)<Integer.parseInt(Cdate)){
            holder.result.setText(list_item_leagues.getScore().getFulltime());
        }
        else if (Integer.parseInt(tx)==Integer.parseInt(Cdate)){
            if (list_item_leagues.getGoalsHomeTeam()!=null && list_item_leagues.getGoalsAwayTeam()!=null ){
                holder.result.append(list_item_leagues.getHomeTeam()+"-"+list_item_leagues.getGoalsAwayTeam());
           } else{
                holder.result.setText(" - "); }
        }*/
    }
    @Override
    public int getItemCount() {
        return listitemLeagues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView t_home,t_away,time,match_date,result,goalshomeTeam,goalsawayTeam,halftime,fulltime,extratime,penalty;
        public ImageView logo_home,logo_away;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t_home=itemView.findViewById(R.id.team_home);
            t_away=itemView.findViewById(R.id.team_away);
            time=itemView.findViewById(R.id.full_Time);
            logo_home=itemView.findViewById(R.id.img_home);
            logo_away=itemView.findViewById(R.id.img_away);
            match_date=itemView.findViewById(R.id.match_date);
            result=itemView.findViewById(R.id.result);
        }
    }
}
