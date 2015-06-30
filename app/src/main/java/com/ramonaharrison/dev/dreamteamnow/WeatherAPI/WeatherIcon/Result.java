package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherIcon;

import com.google.gson.annotations.Expose;

public class Result {

    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}