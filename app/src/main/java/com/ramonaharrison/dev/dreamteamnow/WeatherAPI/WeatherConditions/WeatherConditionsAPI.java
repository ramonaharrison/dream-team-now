package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions;

import com.ramonaharrison.dev.dreamteamnow.WeatherInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by c4q-anthonyf on 6/29/15.
 */
public interface WeatherConditionsAPI {

    @GET("/{lat},{lon}")
    public void getFeed(@Path("lat") double latitude,
            @Path("lon") double longitude,
            Callback<WeatherConditions> response);
}
