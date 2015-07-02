package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class WeatherConditions {

    @Expose
    private Currently currently;
    @Expose
    private Daily daily;


    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }
}