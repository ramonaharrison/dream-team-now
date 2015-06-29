package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

import retrofit.Callback;
import retrofit.http.GET;


/**
 * Created by c4q-anthonyf on 6/27/15.
 */
public interface WeatherAPI {

        @GET("")      //here is the other url part.best way is to start using /
        public void getFeed(Callback<Weather> response);
        //response is the response from the server which is now in the POJO

}
