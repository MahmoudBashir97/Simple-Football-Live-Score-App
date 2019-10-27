package com.example.api_good.Leagues_Adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api_good.Live.fixtures;
import com.example.api_good.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adpt_live_score extends RecyclerView.Adapter<adpt_live_score.ViewHolder> {


    private List<fixtures> mFixtures;
    private Context context;

    public adpt_live_score(List<fixtures> list_item_scores, Context context){
        this.mFixtures=list_item_scores;
        this.context=context;
    }
    @NonNull
    @Override
    public adpt_live_score.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.live_score,viewGroup,false);
        return new ViewHolder(v);
    }

    // onBindViewHolder =  دي بتتكرر بعدد حجم اليست الي انت باعتها
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // onBindViewHolder = تعتبر loop
        // int i = الي راجعه في البراميتر ده بيتغير في كل مره برقم عنصر في اليست

        // هنا بقوله هاتلي من اليست العنصر رقم الي عليه الدور وحطهولي في الموديل الي بعد كده هاخد منه الداتا واعرضها
        fixtures fixtures= mFixtures.get(i);

        // زي كده
        viewHolder.full_time.setText(fixtures.getHomeTeam().getTeam_name());

        viewHolder.team_home.setText(fixtures.getHomeTeam().getTeam_name());

        viewHolder.team_away.setText(fixtures.getAwayTeam().getTeam_name());





        //load = يحمل الصوره من الينك
        //resize = بتدي حجم جديد للصوره وبستخدمه علشان اقلل حجم الصوره علشان تتحمل بسرعه ومتتقلش البرنامج
        //placeholder = لو معرفش يحمل الصوره الي علي النت من الينك لأي سبب هيبدلها بالصوره دي قدام اليوزر
        //into = دي الاداة الي هيتعرض فيها الصوره
        //والي في الغالب ImageView

        Picasso.with(context).load(fixtures.getHomeTeam().getLogo()).resize(25,25).into(viewHolder.img_home);
        Picasso.with(context).load(fixtures.getAwayTeam().getLogo()).resize(25,25).into(viewHolder.img_away);

    }


    @Override
    public int getItemCount() {
        // حجم اليست هنا بتبعتها للنظام علشان يكررلك onBindViewHolder
        return mFixtures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView full_time,team_home,team_away;
        private ImageView img_home,img_away;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            full_time =itemView.findViewById(R.id.full_Time);
            team_home=itemView.findViewById(R.id.team_home);
            team_away=itemView.findViewById(R.id.team_away);
            img_home=itemView.findViewById(R.id.img_home);
            img_away=itemView.findViewById(R.id.img_away);




        }
    }
}
