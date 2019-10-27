package com.example.api_good.views.Frag_ments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.api_good.Leagues_Adapters.adpt_order;
import com.example.api_good.Order.standings;
import com.example.api_good.R;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.example.api_good.views.News;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.api_good.views.detail_Competition.leaguesID;


public class Order_Fragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ImageButton imageButton;
    LinearLayout group_ln;

    public Order_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_order, container, false);


        String id = leaguesID;
        if (leaguesID.equals("1")||leaguesID.equals("132")||leaguesID.equals("136")||leaguesID.equals("137")||leaguesID.equals("194")||leaguesID.equals("195"))
        {


            RetrofitClient.getInstance().get_Order(Integer.parseInt(id),new HandleResponse() {
                @Override
                public void ResponseOK(JsonObject mainObject) {

                    JsonObject apiBody = mainObject.get("api").getAsJsonObject();
                    JsonArray standingsBody = apiBody.get("standings").getAsJsonArray();
                    List<standings> list = new ArrayList<>();

                    for (int s=0;s<standingsBody.size();s++) {

                        JsonArray bb = standingsBody.get(s).getAsJsonArray();

                    for (int i = 0; i < bb.size(); i++) {
                        standings standings = new Gson().fromJson(bb.get(i).getAsJsonObject(), standings.class);
                        list.add(standings);
                    }
                    }
                    recyclerView=(RecyclerView)v.findViewById(R.id.rec_order);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new adpt_order(list,getContext());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void ResponseError(String errorMessage) {
                    Toast.makeText(getContext(), "Check your Internet Connection !", Toast.LENGTH_SHORT).show();

                }
            });
        }else {

        RetrofitClient.getInstance().get_Order(Integer.parseInt(id),new HandleResponse() {
            @Override
            public void ResponseOK(JsonObject mainObject) {

                JsonObject apiBody = mainObject.get("api").getAsJsonObject();
                JsonArray standingsBody = apiBody.get("standings").getAsJsonArray();
                JsonArray bb = standingsBody.get(0).getAsJsonArray();

                List<standings> list = new ArrayList<>();
                for (int i = 0; i < bb.size(); i++) {
                    standings standings = new Gson().fromJson(bb.get(i).getAsJsonObject(), standings.class);
                    list.add(standings);
                }
                recyclerView=(RecyclerView)v.findViewById(R.id.rec_order);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter=new adpt_order(list,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void ResponseError(String errorMessage) {
                Toast.makeText(getContext(), "Check your Internet Connection !", Toast.LENGTH_SHORT).show();

            }
        });
        }
        return v;
    }

}
