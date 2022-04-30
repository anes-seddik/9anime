package com.anime9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeViewHolder> {
    Context context;
    ArrayList<Anime> data;

    public AnimeAdapter(Context context, ArrayList<Anime> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_layout,parent,false);
        AnimeViewHolder holder = new AnimeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {

        holder.anime_name.setText(data.get(position).getAnime_name());
        holder.anime_pic.setImageResource(data.get(position).getAnime_pic());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
