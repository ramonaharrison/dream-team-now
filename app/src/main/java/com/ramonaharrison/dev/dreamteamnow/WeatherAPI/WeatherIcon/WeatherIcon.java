package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherIcon;

import com.google.gson.annotations.Expose;

public class WeatherIcon {

    @Expose
    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}