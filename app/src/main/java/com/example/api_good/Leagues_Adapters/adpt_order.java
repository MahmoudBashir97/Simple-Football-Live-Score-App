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


import com.example.api_good.Order.standings;
import com.example.api_good.R;
import com.squareup.picasso.Picasso;


import org.w3c.dom.Text;

import java.util.List;

public class adpt_order extends RecyclerView.Adapter<adpt_order.ViewHolder> {
    private List<standings> order_stand ;
    private Context context;
    public static  String leaguesID;
    String group;
    public adpt_order(List<standings> order_stand, Context context){
        this.order_stand=order_stand;
        this.context=context;
    }

    @NonNull
    @Override
    public adpt_order.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_singel_row,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        standings st_andings = order_stand.get(i);
        holder.rank.setText(st_andings.getRank() + "");
        Picasso.with(context).load(st_andings.getLogo()).resize(30, 30).into(holder.logo_team);
        holder.t_name.setText(st_andings.getTeamName());
        holder.numb_matches.setText(st_andings.getAll().getMatchsPlayed() + "");
        holder.goal_for.setText(st_andings.getAll().getGoalsFor() + "");
        holder.points.setText(st_andings.getPoints() + "");
        if (!st_andings.getGroup().trim().equals(group)){
            holder.group_ln.setVisibility(View.VISIBLE);
            group=st_andings.getGroup().trim();
            holder.group_name.setText(group);
        }else
            holder.group_ln.setVisibility(View.GONE);
            holder.group_name.setText("");


        String forme = st_andings.getForme();
        //String[] result = forme.split("\\+");

       /* holder.txt_form1.setText("W");
        holder.txt_form1.setBackgroundColor(Color.GREEN);
        holder.txt_form2.setText("D");
        holder.txt_form2.setBackgroundColor(Color.YELLOW);
        holder.txt_form3.setText("L");
        holder.txt_form3.setBackgroundColor(Color.RED);
        holder.txt_form4.setText("D");
        holder.txt_form4.setBackgroundColor(Color.YELLOW);*/
       /* if (result[0].equals("W")) {
            holder.txt_form1.setText(result[0]);
            holder.txt_form1.setBackgroundColor(Color.GREEN);
        }else if(result[0].equals("D")){
            holder.txt_form1.setText(result[0]);
            holder.txt_form1.setBackgroundColor(Color.YELLOW);
        }else if (result[0].equals("L")){
            holder.txt_form1.setText(result[0]);
            holder.txt_form1.setBackgroundColor(Color.RED);
        }

        if (result[1].equals("W")) {
            holder.txt_form2.setText(result[1]);
            holder.txt_form2.setBackgroundColor(Color.GREEN);
        }else if(result[1].equals("D")){
            holder.txt_form2.setText(result[1]);
            holder.txt_form2.setBackgroundColor(Color.YELLOW);
        }else if (result[1].equals("L")){
            holder.txt_form2.setText(result[1]);
            holder.txt_form2.setBackgroundColor(Color.RED);
        }
        if (result[2].equals("W")) {
            holder.txt_form3.setText(result[2]);
            holder.txt_form3.setBackgroundColor(Color.GREEN);
        }else if(result[2].equals("D")){
            holder.txt_form3.setText(result[2]);
            holder.txt_form3.setBackgroundColor(Color.YELLOW);
        }else if (result[2].equals("L")){
            holder.txt_form3.setText(result[2]);
            holder.txt_form3.setBackgroundColor(Color.RED);
        }

        if (result[3].equals("W")) {
            holder.txt_form4.setText(result[3]);
            holder.txt_form4.setBackgroundColor(Color.GREEN);
        }else if(result[3].equals("D")){
            holder.txt_form4.setText(result[3]);
            holder.txt_form4.setBackgroundColor(Color.YELLOW);
        }else if (result[3].equals("L")){
            holder.txt_form4.setText(result[3]);
            holder.txt_form4.setBackgroundColor(Color.RED);
        }
*/
    }

    @Override
    public int getItemCount() {
        return order_stand.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rank,t_name,numb_matches,goal_for,goal_against,points,txt_form1,txt_form2,txt_form3,txt_form4,group_name;
        LinearLayout group_ln;
        ImageView logo_team;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rank=itemView.findViewById(R.id.rank);
            t_name=itemView.findViewById(R.id.team_name);
            numb_matches=itemView.findViewById(R.id.matches);
            goal_for=itemView.findViewById(R.id.goals);
            points=itemView.findViewById(R.id.point);
            logo_team=itemView.findViewById(R.id.flag_image2);
            txt_form1=itemView.findViewById(R.id.txt_form1);
            txt_form2=itemView.findViewById(R.id.txt_form2);
            txt_form3=itemView.findViewById(R.id.txt_form3);
            txt_form4=itemView.findViewById(R.id.txt_form4);
            group_name=itemView.findViewById(R.id.group_name);
            group_ln=itemView.findViewById(R.id.group_ln);

        }
    }
}
