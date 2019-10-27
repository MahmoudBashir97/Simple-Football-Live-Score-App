package com.example.api_good.Leagues_Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api_good.R;
import com.example.api_good.fixture_league.fixtures;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class adpt_Matches extends RecyclerView.Adapter<adpt_Matches.ViewHolder>{

    private Context context;
    private List<fixtures> list_league_match;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public adpt_Matches(Context context, List<fixtures> list_league_match) {
        this.context = context;
        this.list_league_match = list_league_match;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.live_score,viewGroup,false);
        database = FirebaseDatabase.getInstance();
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        fixtures list_fixt=list_league_match.get(i);

        holder.t_home.setText(list_fixt.getHomeTeam().getTeam_name());
        holder.t_away.setText(list_fixt.getAwayTeam().getTeam_name());
        Picasso.with(context).load(list_fixt.getHomeTeam().getLogo()).resize(30,30).into(holder.logo_home);
        Picasso.with(context).load(list_fixt.getAwayTeam().getLogo()).resize(30,30).into(holder.logo_away);

        String spl=list_fixt.getEvent_date();
        String[] separated=spl.split("T");
        String tx=separated[0];

        String[] arr=separated[1].split(":00\\+");
        String tx1=arr[0];

        SimpleDateFormat cdate=new SimpleDateFormat("yyyy-MM-dd");
        String   Current_date=cdate.format(new Date());
        holder.txt_date.append("Date :"+Current_date);

        int league_ID=list_fixt.getLeague_id();
        Intent intent = new Intent("League_ID");
        intent.putExtra("leagues_id",league_ID);
        intent.putExtra("match_date",tx);

        myRef = database.getReference(String.valueOf(league_ID));
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("league_ID",String.valueOf(league_ID));
        hashMap.put("match_date",tx);
        myRef.setValue(hashMap);
    }

    @Override
    public int getItemCount() {
        return list_league_match.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_date;
        public TextView t_home,t_away,time,match_date,result,goalshomeTeam,goalsawayTeam,halftime,fulltime,extratime,penalty;
        public ImageView logo_home,logo_away;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_date=itemView.findViewById(R.id.txt_date);
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
