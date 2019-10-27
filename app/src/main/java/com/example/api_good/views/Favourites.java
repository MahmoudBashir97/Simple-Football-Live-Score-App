package com.example.api_good.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.api_good.R;

public class Favourites extends AppCompatActivity {
    ImageButton imageButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        imageButton=(ImageButton)findViewById(R.id.img_bu);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Favourites.this,Act_with_navigation.class);
                startActivity(intent);
            }
        });
    }
}
