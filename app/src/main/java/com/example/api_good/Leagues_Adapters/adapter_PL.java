package com.example.api_good.Leagues_Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.api_good.R;
import com.example.api_good.list_items.listitem_PL;

import java.util.List;

public class adapter_PL extends RecyclerView.Adapter<adapter_PL.ViewHolder> {
    private List<listitem_PL> listitem_pls;
    private Context context;

    public adapter_PL(List<listitem_PL> listitems,Context context){
        this.listitem_pls=listitems;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_primier_league,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        listitem_PL listItem=listitem_pls.get(i);
        holder.txt_teamname.setText(listItem.getName());


    }

    @Override
    public int getItemCount() {
        return listitem_pls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_teamname;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_teamname=(TextView)itemView.findViewById(R.id.t_name);
        }
    }
}
