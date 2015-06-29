package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Pws {

    @Expose
    private List<Station_> station = new ArrayList<Station_>();

    /**
     *
     * @return
     * The station
     */
    public List<Station_> getStation() {
        return station;
    }

    /**
     *
     * @param station
     * The station
     */
    public void setStation(List<Station_> station) {
        this.station = station;
    }

}