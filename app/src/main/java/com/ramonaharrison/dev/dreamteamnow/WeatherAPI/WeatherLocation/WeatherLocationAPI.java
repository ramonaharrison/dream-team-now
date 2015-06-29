package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by c4q-anthonyf on 6/27/15.
 */
public interface WeatherLocationAPI {

    @GET("/geolookup/q/{lat},{lon}.json")
    public void getFeed(@Path("lat") Double lat, @Path("lon") Double lon, Callback<WeatherLocation> response);

}
