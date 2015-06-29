package com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation;

import com.google.gson.annotations.Expose;

public class NearbyWeatherStations {

@Expose
private Airport airport;
@Expose
private Pws pws;

/**
* 
* @return
* The airport
*/
public Airport getAirport() {
return airport;
}

/**
* 
* @param airport
* The airport
*/
public void setAirport(Airport airport) {
this.airport = airport;
}

/**
* 
* @return
* The pws
*/
public Pws getPws() {
return pws;
}

/**
* 
* @param pws
* The pws
*/
public void setPws(Pws pws) {
this.pws = pws;
}

}