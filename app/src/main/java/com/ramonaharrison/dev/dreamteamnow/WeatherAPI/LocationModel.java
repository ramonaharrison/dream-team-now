package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

import com.google.gson.annotations.Expose;

/**
 * Created by c4q-anthonyf on 6/27/15.
 */
public class LocationModel {

    @Expose
    private String country;
    @Expose
    private String state;
    @Expose
    private String city;
    @Expose
    private String zip;
    @Expose
    private String requesturl;

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getRequesturl() {
        return requesturl;
    }
}
