package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions;

import com.google.gson.annotations.Expose;

/**
 * Created by c4q-anthonyf on 7/2/15.
 */
public class Datum__ {

    @Expose
    private String icon;
    @Expose
    private String summary;
    @Expose
    private Double temperatureMin;
    @Expose
    private Double temperatureMax;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }
}
