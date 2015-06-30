package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions;

import com.google.gson.annotations.Expose;

public class WeatherConditions {

    @Expose
    private Currently currently;


    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }
}