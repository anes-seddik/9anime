package com.anime9;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list);

        ImageButton search = findViewById(R.id.search_btn);
        ImageButton profile = findViewById(R.id.profile);
        EditText search_bar = findViewById(R.id.koko);
        RecyclerView recyclerView = findViewById(R.id.animes);
        RelativeLayout bottom_bar = findViewById(R.id.bottom_bar);

        SharedPreferences log =getSharedPreferences("log_status",Context.MODE_PRIVATE);
        Boolean log_status = log.getBoolean("log_status",false);
        if (log_status.equals(true)){
            profile.setVisibility(View.VISIBLE);
        }
        search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (search_bar.getVisibility() == View.INVISIBLE){
                    search_bar.setVisibility(View.VISIBLE);


                }
                else if (search_bar.getVisibility() == View.GONE){
                    search_bar.setVisibility(View.VISIBLE);

                }
                else if (search_bar.getVisibility() == View.VISIBLE){
                    String anime_val = search_bar.getText().toString().trim();
                    if (anime_val.isEmpty()){
                        Toast.makeText(list.this,"enter an anime..",Toast.LENGTH_LONG).show(); }
                    else {
                        Intent i = new Intent(list.this,list.class);
                    }
                }
            }
        });


        //going to page1 by clicking on the logo
        ImageView logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(list.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        

        RecyclerView animes_list = findViewById(R.id.animes);

        ArrayList<Anime> animes = new ArrayList<>();
        animes.add(new Anime("Naruto",R.drawable.naruto1));
        animes.add(new Anime("Naruto shippuden",R.drawable.naruto2));
        animes.add(new Anime("Naruto The movie",R.drawable.naruto3));
        animes.add(new Anime("Death Note",R.drawable.death_note));
        animes.add(new Anime("shingeki no kyojin S1",R.drawable.aot1));
        animes.add(new Anime("shingeki no kyojin S4",R.drawable.aot2));
        animes.add(new Anime("Tokyo revengers",R.drawable.tokyo_rev));
        animes.add(new Anime("One piece",R.drawable.onepiece));
        animes.add(new Anime("Jujutsu no kaisen",R.drawable.jujutsu));
        animes.add(new Anime("Dr.Stone",R.drawable.dr_stone));


        AnimeAdapter adapter = new AnimeAdapter(list.this,animes);

        animes_list.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);

        animes_list.setLayoutManager(layoutManager);
    }

}