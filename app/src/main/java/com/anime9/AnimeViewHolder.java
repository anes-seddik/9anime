package com.anime9;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimeViewHolder extends RecyclerView.ViewHolder{
    public TextView anime_name;
    public ImageView anime_pic;
    public AnimeViewHolder(@NonNull  View itemView) {
        super(itemView);
        anime_name= itemView.findViewById(R.id.anime_name);
        anime_pic= itemView.findViewById(R.id.anime_pic);
    }
}
