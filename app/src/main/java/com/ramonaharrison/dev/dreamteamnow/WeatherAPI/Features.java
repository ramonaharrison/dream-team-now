package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

import com.google.gson.annotations.Expose;

public class Features {

    @Expose
    private Integer geolookup;

    /**
     *
     * @return
     * The geolookup
     */
    public Integer getGeolookup() {
        return geolookup;
    }

    /**
     *
     * @param geolookup
     * The geolookup
     */
    public void setGeolookup(Integer geolookup) {
        this.geolookup = geolookup;
    }

}