package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Airport {

    @Expose
    private List<Station> station = new ArrayList<Station>();

    /**
     *
     * @return
     * The station
     */
    public List<Station> getStation() {
        return station;
    }

    /**
     *
     * @param station
     * The station
     */
    public void setStation(List<Station> station) {
        this.station = station;
    }

}