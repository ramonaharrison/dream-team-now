package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation;

import com.google.gson.annotations.Expose;

public class WeatherLocation {

    @Expose
    private Location location;


    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

}

