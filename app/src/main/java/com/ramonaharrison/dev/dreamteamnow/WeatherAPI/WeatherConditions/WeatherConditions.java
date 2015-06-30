package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherConditions {

    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;


    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

}