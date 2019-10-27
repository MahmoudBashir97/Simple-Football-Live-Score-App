package com.example.api_good.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.api_good.R;

public class Settings extends AppCompatActivity {
    ImageButton imageButton;
    Intent intent;
    private SwitchCompat aSwitch;
    LinearLayout Lin_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.darktheme);
        }else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Lin_notification=(LinearLayout)findViewById(R.id.liner2);
        Lin_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.this,Notifications.class);
                startActivity(i);
                finish();
            }
        });
        imageButton=(ImageButton)findViewById(R.id.img_bu);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Settings.this,Act_with_navigation.class);
                startActivity(intent);
            }
        });


        aSwitch=(SwitchCompat) findViewById(R.id.mswitch);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();}

            }
        });
    }

    private void restartApp() {
        Intent intent=new Intent(getApplicationContext(),Settings.class);
        startActivity(intent);
        finish();
    }
}
