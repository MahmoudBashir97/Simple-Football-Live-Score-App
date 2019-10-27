package com.example.api_good.Leagues_Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api_good.Live.fixtures;
import com.example.api_good.R;
import com.example.api_good.leagues.leagues;
import com.example.api_good.views.detail_Competition;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Competiton_Adapter extends RecyclerView.Adapter<Competiton_Adapter.ViewHolder> {

    private List<leagues> mleagues;
    private Context context;
    public Competiton_Adapter(List<leagues> mleagues, Context context){
        this.mleagues=mleagues;
        this.context=context;
    }

    @NonNull
    @Override
    public Competiton_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.competition_rec_layout,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        final leagues lea_gues=mleagues.get(i);
        Picasso.with(context).load(lea_gues.getLogo()).resize(30,30).into(holder.flag);
        holder.comp_name.setText(lea_gues.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, detail_Competition.class);
                i.putExtra("leagues_id",lea_gues.getLeague_id());
                i.putExtra("league_name",lea_gues.getName());
                context.startActivity(i);

              /* Bundle args=new Bundle();
                args.putString("leagues_id", String.valueOf(lea_gues.getLeague_id()));

                Fragment fragment=new matches_Fragment();
                fragment.setArguments(args);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mleagues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flag;
        TextView comp_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag=itemView.findViewById(R.id.flag_competition);
            comp_name=itemView.findViewById(R.id.name_competition);
        }
    }
}
