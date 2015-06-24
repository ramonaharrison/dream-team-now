package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by c4q-anthonyf on 6/23/15.
 */
public class WeatherInfo extends CardInfo {

    private static final String API_KEY = "e745be7dec5ece10d31a8fa4f48a8649";
    private String JSON;
    private double longitude;
    private double latitude;

    public WeatherInfo(Context context){
        context = context.getApplicationContext();
        setType("weather");
        LocationManager lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
