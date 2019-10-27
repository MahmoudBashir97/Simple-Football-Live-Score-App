package com.example.api_good.views;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.api_good.R;
import com.example.api_good.views.Frag_ments.Line_up;
import com.example.api_good.views.Frag_ments.View_Pager;

import com.example.api_good.views.Frag_ments.statistic_match;

public class Match_information extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_info);

        tabLayout=(TabLayout)findViewById(R.id.tablay);
        viewPager=(ViewPager)findViewById(R.id.viewpage);

        View_Pager adapter=new View_Pager(getSupportFragmentManager());
        adapter.addFragment(new statistic_match(),"الإحصائيات");
        adapter.addFragment(new Line_up(),"الخطة");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
