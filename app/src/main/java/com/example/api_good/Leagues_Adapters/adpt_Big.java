package com.example.api_good.Leagues_Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_good.R;
import com.example.api_good.fixture_league.Live_Response;
import com.example.api_good.fixture_league.fixtures;
import com.example.api_good.leagues.leagues_Response;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.example.api_good.views.Act_with_navigation;
import com.example.api_good.views.Match_information;
import com.example.api_good.views.detail_Competition;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class adpt_Big  extends RecyclerView.Adapter<adpt_Big.ViewHolder> {
    private List<fixtures> listitemLeagues;
    private Context context;
    static String Round = "none";
    RecyclerView.RecycledViewPool viewPool;

    public adpt_Big(List<fixtures> listitemLeagues, Context context) {
        this.listitemLeagues = listitemLeagues;
        this.context = context;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public adpt_Big.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inner_row,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final adpt_Big.ViewHolder holder, final int i) {
        fixtures list_item_leagues=listitemLeagues.get(i);


        if (!list_item_leagues.getRound().trim().equals(Round)){
            holder.fifa_nav.setVisibility(View.VISIBLE);
            Round = list_item_leagues.getRound().trim();
        }else holder.fifa_nav.setVisibility(View.GONE);



        holder.t_home.setText(list_item_leagues.getHomeTeam().getTeam_name());
        holder.t_away.setText(list_item_leagues.getAwayTeam().getTeam_name());
        Picasso.with(context).load(list_item_leagues.getHomeTeam().getLogo()).resize(30,30).into(holder.logo_home);
        Picasso.with(context).load(list_item_leagues.getAwayTeam().getLogo()).resize(30,30).into(holder.logo_away);




        holder.result.setText(list_item_leagues.getScore().getFulltime());

        holder.result.setText("-");
        // cairo_time
        long ms = list_item_leagues.getEvent_timestamp();
        long s = ms % 60;
        long m = (ms / 60) % 60;
        long h = ((ms / (60 * 60)) % 24)+2;
        long hlftime=list_item_leagues.getFirstHalfStart();

        String time_Stamp = String.format("%d:%02d", h, m, s);
        holder.time.setText(time_Stamp);

       String league_id= String.valueOf(list_item_leagues.getLeague_id());

        String elapsed= String.valueOf(list_item_leagues.getElapsed());

        if (ms==hlftime){
            holder.time.setText(elapsed);
            holder.time.setTextColor(Color.GREEN);

            holder.result.setText(list_item_leagues.getScore().getHalftime());
        }else holder.time.setText(time_Stamp);

        if (list_item_leagues.getElapsed()>=90){
            holder.result.setText(list_item_leagues.getScore().getFulltime());
        }else holder.result.setText(list_item_leagues.getScore().getHalftime());

     /*   Intent intent=new Intent(context,Act_with_navigation.class);
        intent.putExtra("league_id",list_item_leagues.getLeague_id());
        context.startActivity(intent);
        ((Act_with_navigation)context).finish();*/


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Match_information.class);
                context.startActivity(intent);
            }
        });

        RetrofitClient.getInstance().getleagues(Integer.parseInt(league_id), new HandleResponse() {
            @Override
            public void ResponseOK(JsonObject mainObject) {
                leagues_Response leaguesResponse=new Gson().fromJson(mainObject,leagues_Response.class);
                String leaguename=leaguesResponse.getApi().getLeagues().get(0).getName();
                holder.txt_botola_name.setText(leaguename);
                String botola_league=leaguesResponse.getApi().getLeagues().get(0).getLogo();
                Picasso.with(context).load(botola_league).resize(30,25).into(holder.img_botola);
            }

            @Override
            public void ResponseError(String errorMessage) {

            }
        });




    }


    @Override
    public int getItemCount() {
        return listitemLeagues.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
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
