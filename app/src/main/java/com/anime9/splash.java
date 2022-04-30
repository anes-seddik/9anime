package com.anime9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class splash extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashfile);

        Animation topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        Animation bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        ImageView logo = findViewById(R.id.imageView);
        TextView txt1 = findViewById(R.id.text1);
        TextView txt2 = findViewById(R.id.text2);

        topAnim.setDuration(2000);
        bottomAnim.setDuration(1500);

        logo.setAnimation(topAnim);
        txt1.setAnimation(bottomAnim);
        txt2.setAnimation(bottomAnim);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}