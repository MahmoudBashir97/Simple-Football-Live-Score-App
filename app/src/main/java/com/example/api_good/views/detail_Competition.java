package com.example.api_good.views;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;
import com.example.api_good.R;
import com.example.api_good.views.Frag_ments.Order_Fragment;
import com.example.api_good.views.Frag_ments.View_Pager;
import com.example.api_good.views.Frag_ments.matches_Fragment;

public class detail_Competition extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static  String leaguesID;
    ImageButton imageButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__competition);

        tabLayout=(TabLayout)findViewById(R.id.tablay);
        viewPager=(ViewPager)findViewById(R.id.viewpage);
        imageButton=(ImageButton)findViewById(R.id.img_bu);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);



        leaguesID = getIntent().getExtras().get("leagues_id").toString();
        String league_name= getIntent().getExtras().get("league_name").toString();
        toolbar.setTitle("          "+league_name);

      if (leaguesID=="1"||leaguesID=="132"||leaguesID=="136"||leaguesID=="137"||leaguesID=="194"||leaguesID=="195"){

          View_Pager adapter=new View_Pager(getSupportFragmentManager());
          adapter.addFragment(new matches_Fragment(),"المباريات");
          adapter.addFragment(new Order_Fragment(),"المجموعات");
          viewPager.setAdapter(adapter);
          tabLayout.setupWithViewPager(viewPager);
      }
      else {
          View_Pager adapter=new View_Pager(getSupportFragmentManager());
          adapter.addFragment(new matches_Fragment(),"المباريات");
          adapter.addFragment(new Order_Fragment(),"الترتيب");
          viewPager.setAdapter(adapter);
          tabLayout.setupWithViewPager(viewPager);
      }


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(detail_Competition.this,Competition.class);
                startActivity(intent);
            }
        });
    }
}
