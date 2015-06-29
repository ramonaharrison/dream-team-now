package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station_ {

    @Expose
    private String neighborhood;
    @Expose
    private String city;
    @Expose
    private String state;
    @Expose
    private String country;
    @Expose
    private String id;
    @Expose
    private Double lat;
    @Expose
    private Double lon;
    @SerializedName("distance_km")
    @Expose
    private Integer distanceKm;
    @SerializedName("distance_mi")
    @Expose
    private Integer distanceMi;

    /**
     *
     * @return
     * The neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     *
     * @param neighborhood
     * The neighborhood
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The distanceKm
     */
    public Integer getDistanceKm() {
        return distanceKm;
    }

    /**
     *
     * @param distanceKm
     * The distance_km
     */
    public void setDistanceKm(Integer distanceKm) {
        this.distanceKm = distanceKm;
    }

    /**
     *
     * @return
     * The distanceMi
     */
    public Integer getDistanceMi() {
        return distanceMi;
    }

    /**
     *
     * @param distanceMi
     * The distance_mi
     */
    public void setDistanceMi(Integer distanceMi) {
        this.distanceMi = distanceMi;
    }

}