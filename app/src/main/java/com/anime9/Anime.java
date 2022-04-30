package com.anime9;

public class Anime {
    private String anime_name;
    private int anime_pic;
    public Anime(String anime_name, int anime_pic){
        this.anime_name=anime_name;
        this.anime_pic=anime_pic;
    }

    public String getAnime_name() {
        return anime_name;
    }

    public int getAnime_pic() {
        return anime_pic;
    }
}
