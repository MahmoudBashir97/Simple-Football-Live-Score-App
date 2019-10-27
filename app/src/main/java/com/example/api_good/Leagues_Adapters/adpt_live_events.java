package com.example.api_good.Leagues_Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.api_good.Live.fixtures;
import com.example.api_good.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adpt_live_events extends RecyclerView.Adapter<adpt_live_events.ViewHolder> {
    private List<fixtures> listitemLeagues;
    private Context context;
    static String Round = "none";
    RecyclerView.RecycledViewPool viewPool;

    public adpt_live_events(List<fixtures> listitemLeagues, Context context) {
        this.listitemLeagues = listitemLeagues;
        this.context = context;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inner_row,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        fixtures list_item_leagues=listitemLeagues.get(i);


        if (!list_item_leagues.getRound().trim().equals(Round)){
            holder.fifa_nav.setVisibility(View.VISIBLE);
            Round = list_item_leagues.getRound().trim();
        }else holder.fifa_nav.setVisibility(View.GONE);



        holder.t_home.setText(list_item_leagues.getHomeTeam().getTeam_name());
        holder.t_away.setText(list_item_leagues.getAwayTeam().getTeam_name());
        Picasso.with(context).load(list_item_leagues.getHomeTeam().getLogo()).resize(30,30).into(holder.logo_home);
        Picasso.with(context).load(list_item_leagues.getAwayTeam().getLogo()).resize(30,30).into(holder.logo_away);



        holder.result.setText("-");
        // cairo_time
        long ms = list_item_leagues.getEvent_timestamp()+7200000;
        long s = ms % 60;
        long m = (ms / 60) % 60;
        long h = (ms / (60 * 60)) % 24;
        long hlftime=list_item_leagues.getFirstHalfStart()+7200000;
        String time_Stamp = String.format("%d:%02d", h, m, s);
        holder.time.setText(time_Stamp);
        String elapsed= String.valueOf(list_item_leagues.getElapsed());

        if (ms==hlftime){
            holder.time.setText(elapsed);
            holder.time.setTextColor(Color.GREEN);

            if(elapsed.equals("45")){
                holder.result.setText(list_item_leagues.getScore().getHalftime());
            }else {holder.result.append(list_item_leagues.getGoalsHomeTeam()+"-"+list_item_leagues.getGoalsAwayTeam());
            }
        }else holder.time.setText(time_Stamp);

        if (list_item_leagues.getElapsed()>=90){
              holder.result.setText(list_item_leagues.getScore().getFulltime());
        }else holder.result.setText(list_item_leagues.getScore().getHalftime());
       // holder.time.setText(list_item_leagues.getElapsed());

    }

    @Override
    public int getItemCount() {
        return listitemLeagues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout fifa_nav ,inner_lin;
        public TextView t_home,t_away,time,match_date,result,txt_botola_name,goalshomeTeam,goalsawayTeam,halftime,fulltime,extratime,penalty;
        public ImageView logo_home,logo_away,img_botola;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fifa_nav=itemView.findViewById(R.id.fifa_nav);
            t_home=itemView.findViewById(R.id.team_home);
            t_away=itemView.findViewById(R.id.team_away);
            time=itemView.findViewById(R.id.full_Time);
            logo_home=itemView.findViewById(R.id.img_home);
            logo_away=itemView.findViewById(R.id.img_away);
            match_date=itemView.findViewById(R.id.match_date);
            result=itemView.findViewById(R.id.result);
            img_botola=itemView.findViewById(R.id.img_botola);
            txt_botola_name=itemView.findViewById(R.id.txt_botola_name);
            inner_lin=itemView.findViewById(R.id.inner_lin);

        }
    }
}
