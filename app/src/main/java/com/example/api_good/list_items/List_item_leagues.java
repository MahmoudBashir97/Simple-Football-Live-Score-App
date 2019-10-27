package com.example.api_good.list_items;

public class List_item_leagues {
    private  String T_home;
    private String URL_img_home;
    private String URL_img_away;
    private  String T_away;

    public List_item_leagues(String t_home, String URL_img_home, String URL_img_away, String t_away) {
        T_home = t_home;
        this.URL_img_home = URL_img_home;
        this.URL_img_away = URL_img_away;
        T_away = t_away;
    }

    public String getT_home() {
        return T_home;
    }

    public String getURL_img_home() {
        return URL_img_home;
    }

    public String getURL_img_away() {
        return URL_img_away;
    }

    public String getT_away() {
        return T_away;
    }
}
