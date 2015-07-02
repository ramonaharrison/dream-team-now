package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-anthonyf on 7/2/15.
 */
public class Daily {

    @Expose
    private String icon;
    @Expose
    private String summary;
    @Expose
    private List<Datum__> data = new ArrayList<Datum__>();

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }
}
