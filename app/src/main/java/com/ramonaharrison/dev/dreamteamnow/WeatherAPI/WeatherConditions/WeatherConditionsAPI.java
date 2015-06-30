package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions;

import com.ramonaharrison.dev.dreamteamnow.WeatherInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by c4q-anthonyf on 6/29/15.
 */
public interface WeatherConditionsAPI {

    @GET(WeatherInfo.CONDITIONS + "{country}/{state}/{city}.json")
    public void getFeed(@Path("country") String country,
            @Path("state") String state,
            @Path("city") String city,
            Callback<WeatherConditions> response);


}
