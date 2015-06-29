package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by c4q-anthonyf on 6/27/15.
 */
public interface LocationAPI {

    @GET("/geolookup/q/{lat},{lon}.json")
    public void getFeed(@Path("lat") Double lat, @Path("lon") Double lon, Callback<Location> response);

}
