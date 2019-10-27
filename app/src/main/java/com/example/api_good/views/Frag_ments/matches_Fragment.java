package com.example.api_good.views.Frag_ments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.api_good.Leagues_Adapters.Competiton_Adapter;
import com.example.api_good.Leagues_Adapters.Egypt_PL_adapter;
import com.example.api_good.R;
import com.example.api_good.leagues.leagues;
import com.example.api_good.leagues.leagues_Response;
import com.example.api_good.matches_model.Live_Response;
import com.example.api_good.restfulApis.HandleResponse;
import com.example.api_good.restfulApis.RetrofitClient;
import com.example.api_good.views.Competition;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.api_good.views.detail_Competition.leaguesID;

public class matches_Fragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public matches_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_matches, container, false);

       //     Toast.makeText(getContext(), ""+leaguesID, Toast.LENGTH_SHORT).show();
        if (leaguesID=="1"||leaguesID=="132"||leaguesID=="136"||leaguesID=="137"||leaguesID=="194"||leaguesID=="195"){
            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
        }else {
            RetrofitClient.getInstance().get_matches(Integer.parseInt(leaguesID), new HandleResponse() {
                @Override
                public void ResponseOK(JsonObject mainObject) {
                    Live_Response live_response=new Gson().fromJson(mainObject,Live_Response.class);

                    recyclerView = (RecyclerView) v.findViewById(R.id.rec_matches);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new Egypt_PL_adapter(live_response.getApi().getFixtures(),getContext());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void ResponseError(String errorMessage) {

                }
            });}
        return v;
    }
}
