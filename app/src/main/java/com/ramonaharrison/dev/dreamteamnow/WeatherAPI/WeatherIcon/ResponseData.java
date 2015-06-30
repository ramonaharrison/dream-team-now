package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherIcon;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class ResponseData {

    @Expose
    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}