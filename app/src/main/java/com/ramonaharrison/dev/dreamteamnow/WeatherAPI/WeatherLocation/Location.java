package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation;


import com.google.gson.annotations.Expose;

public class Location {

    @Expose
    private String type;
    @Expose
    private String country;
    @Expose
    private String state;
    @Expose
    private String city;
    @Expose
    private String zip;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}