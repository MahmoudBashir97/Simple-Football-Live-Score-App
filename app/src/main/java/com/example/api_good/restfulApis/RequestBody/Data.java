package com.example.api_good.restfulApis.RequestBody;

public class Data {

    private String action;
    private String lang;
    private int page;

    public Data(String action, String lang) {
        this.action = action;
        this.lang = lang;
    }

    public Data(String action, String lang, int page) {
        this.action = action;
        this.lang = lang;
        this.page = page;
    }
}