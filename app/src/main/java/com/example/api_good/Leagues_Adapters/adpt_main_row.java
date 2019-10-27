package com.example.api_good.Leagues_Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api_good.R;
import com.example.api_good.fixture_league.fixtures;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class adpt_main_row  extends RecyclerView.Adapter<adpt_main_row.ViewHolder>  {

    private List<fixtures> listitemLeagues;
    private Context context;
    public adpt_main_row(List<fixtures> listitemLeagues, Context context) {
        this.listitemLeagues = listitemLeagues;
        this.context = context;
    }

    @NonNull
    @Override
    public adpt_main_row.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inner_row,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        fixtures list_item_leagues=listitemLeagues.get(i);

        holder.t_home.setText(list_item_leagues.getHomeTeam().getTeam_name());
        holder.t_away.setText(list_item_leagues.getAwayTeam().getTeam_name());
        Picasso.with(context).load(list_item_leagues.getHomeTeam().getLogo()).resize(30,30).into(holder.logo_home);
        Picasso.with(context).load(list_item_leagues.getAwayTeam().getLogo()).resize(30,30).into(holder.logo_away);

        holder.result.setText(list_item_leagues.getScore().getFulltime());


        //set match time
        long ms = list_item_leagues.getEvent_timestamp();
        long s = ms % 60;
        long m = (ms / 60) % 60;
        long h = (ms / (60 * 60)) % 24;

        String time_Stamp = String.format("%d:%02d", h, m, s);
        holder.time.setText(time_Stamp);


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
