package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherIcon;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by c4q-anthonyf on 6/30/15.
 */
public interface WeatherIconAPI {

    @GET("/images")
    public void getFeed(@Query("v=1.0&q") String query, Callback<WeatherIcon> response);

}
