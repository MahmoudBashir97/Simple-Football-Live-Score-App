package com.example.api_good.views.Frag_ments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.api_good.R;

public class Line_up extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    public Line_up() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_line_up, container, false);
        tabLayout=(TabLayout)v.findViewById(R.id.tablay);
        viewPager=(ViewPager)v.findViewById(R.id.viewpager);

        View_Pager adapter=new View_Pager(getFragmentManager());
        adapter.addFragment(new statistic_match(),"Ahly");
        adapter.addFragment(new statistic_match(),"Itl3_bara2");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        return v;
    }

}
