package com.ramonaharrison.dev.dreamteamnow.WeatherAPI;

/**
 * Created by c4q-anthonyf on 6/27/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @Expose
    @SerializedName("temp_f")
    private String tempF;
    @Expose
    @SerializedName("temp_c")
    private String tempC;
    @Expose
    private String weather;
    @Expose
    @SerializedName("relative_humidity")
    private String relativeHumidity;
    @Expose
    @SerializedName("wind_mph")
    private String windMPH;
    @Expose
    @SerializedName("wind_kph")
    private String windKPH;
    @Expose
    @SerializedName("icon_url")
    private String iconURL;

    public String getTempF() {
        return tempF;
    }

    public String getTempC() {
        return tempC;
    }

    public String getWeather() {
        return weather;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public String getWindMPH() {
        return windMPH;
    }

    public String getWindKPH() {
        return windKPH;
    }

    public String getIconURL() {
        return iconURL;
    }
}