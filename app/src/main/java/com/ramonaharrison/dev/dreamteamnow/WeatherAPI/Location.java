package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @Expose
    private String type;
    @Expose
    private String country;
    @SerializedName("country_iso3166")
    @Expose
    private String countryIso3166;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @Expose
    private String state;
    @Expose
    private String city;
    @SerializedName("tz_short")
    @Expose
    private String tzShort;
    @SerializedName("tz_long")
    @Expose
    private String tzLong;
    @Expose
    private String lat;
    @Expose
    private String lon;
    @Expose
    private String zip;
    @Expose
    private String magic;
    @Expose
    private String wmo;
    @Expose
    private String l;
    @Expose
    private String requesturl;
    @Expose
    private String wuiurl;
    @SerializedName("nearby_weather_stations")
    @Expose
    private NearbyWeatherStations nearbyWeatherStations;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
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
     * The countryIso3166
     */
    public String getCountryIso3166() {
        return countryIso3166;
    }

    /**
     *
     * @param countryIso3166
     * The country_iso3166
     */
    public void setCountryIso3166(String countryIso3166) {
        this.countryIso3166 = countryIso3166;
    }

    /**
     *
     * @return
     * The countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @param countryName
     * The country_name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
     * The tzShort
     */
    public String getTzShort() {
        return tzShort;
    }

    /**
     *
     * @param tzShort
     * The tz_short
     */
    public void setTzShort(String tzShort) {
        this.tzShort = tzShort;
    }

    /**
     *
     * @return
     * The tzLong
     */
    public String getTzLong() {
        return tzLong;
    }

    /**
     *
     * @param tzLong
     * The tz_long
     */
    public void setTzLong(String tzLong) {
        this.tzLong = tzLong;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public String getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The zip
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     * The zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     * The magic
     */
    public String getMagic() {
        return magic;
    }

    /**
     *
     * @param magic
     * The magic
     */
    public void setMagic(String magic) {
        this.magic = magic;
    }

    /**
     *
     * @return
     * The wmo
     */
    public String getWmo() {
        return wmo;
    }

    /**
     *
     * @param wmo
     * The wmo
     */
    public void setWmo(String wmo) {
        this.wmo = wmo;
    }

    /**
     *
     * @return
     * The l
     */
    public String getL() {
        return l;
    }

    /**
     *
     * @param l
     * The l
     */
    public void setL(String l) {
        this.l = l;
    }

    /**
     *
     * @return
     * The requesturl
     */
    public String getRequesturl() {
        return requesturl;
    }

    /**
     *
     * @param requesturl
     * The requesturl
     */
    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl;
    }

    /**
     *
     * @return
     * The wuiurl
     */
    public String getWuiurl() {
        return wuiurl;
    }

    /**
     *
     * @param wuiurl
     * The wuiurl
     */
    public void setWuiurl(String wuiurl) {
        this.wuiurl = wuiurl;
    }

    /**
     *
     * @return
     * The nearbyWeatherStations
     */
    public NearbyWeatherStations getNearbyWeatherStations() {
        return nearbyWeatherStations;
    }

    /**
     *
     * @param nearbyWeatherStations
     * The nearby_weather_stations
     */
    public void setNearbyWeatherStations(NearbyWeatherStations nearbyWeatherStations) {
        this.nearbyWeatherStations = nearbyWeatherStations;
    }

}