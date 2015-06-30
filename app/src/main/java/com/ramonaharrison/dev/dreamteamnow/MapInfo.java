package com.ramonaharrison.dev.dreamteamnow;

import android.location.Location;

/**
 * Created by Ramona Harrison
 * on 6/28/15.
 */
public class MapInfo extends CardInfo {

    private String name;
    private Location location;
    private double longitude;
    private double latitude;

    public MapInfo(String name, Location location) {
        setType("map");
        setPriority(3);
        this.name = name;
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
