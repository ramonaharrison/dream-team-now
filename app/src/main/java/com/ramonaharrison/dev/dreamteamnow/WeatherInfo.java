package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.util.Log;

import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.CurrentObservation;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.WeatherConditions;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.WeatherConditionsAPI;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation.Location;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation.WeatherLocation;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation.WeatherLocationAPI;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by c4q-anthonyf on 6/23/15.
 */

    //TODO: Feature: add new weather cards based on city
    //TODO: 4 day forecast

public class WeatherInfo extends CardInfo {
    private final Context context;
    private static final String OWM_KEY = "8e05118212e07ce3";
    private static final String WEATHER_LAST_LOCATION = "weather_last_location.txt";
    private static final String GEOENDPOINT = "http://api.wunderground.com/api/" + OWM_KEY;
    public static final String GEOLOOKUP = "/geolookup/q/";
    public static final String FORECAST = "/forecast/q/";
    public static final String CONDITIONS = "/conditions/q/";

    //location data
    private double longitude;
    private double latitude;
    private String country;
    private String state;
    private String city;
    private String zip;

    //data for current conditions
    private String tempF;
    private String tempC;
    private String weather;
    private String humidity;
    private String windMPH;
    private String windKPH;
    private String conditionIconURL;
    private boolean isMetric;

    //data for 4 day forecast
    private String[] highF;
    private String[] highC;
    private String[] lowF;
    private String[] lowC;
    private String[] condition;
    private String[] icon_urls;

    //data booleans
    private boolean conditionsComplete;
    private boolean locationComplete;
    private boolean forecastComplete;

    public WeatherInfo(Context context) {
        this.context = context.getApplicationContext();
        setType("weather");
        isMetric = false;
        conditionsComplete = false;
        locationComplete = false;
        forecastComplete = false;
        setPriority(1);
        android.location.Location weather = getLocation();
        setLatLon(weather);

        //Retrofit section start from here...

        retrofitWeatherLocation();
    }

    private void retrofitWeatherConditions() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(GEOENDPOINT)
                .build();

        WeatherConditionsAPI weatherConditionsAPI = restAdapter.create(WeatherConditionsAPI.class);

        weatherConditionsAPI.getFeed(country.replace(" ","_"), state.replace(" ", "_"), city.replace(" ","_"), new Callback<WeatherConditions>() {

            @Override
            public void success(WeatherConditions weatherConditions, Response response) {
                CurrentObservation cObs = weatherConditions.getCurrentObservation();

                tempC = cObs.getTempC().intValue() + "°";
                tempF = cObs.getTempF().intValue() + "°";
                weather = cObs.getWeather();
                humidity = cObs.getRelativeHumidity();
                windMPH = cObs.getWindMph().intValue() + " mph";
                windKPH = cObs.getWindKph().intValue() + " kph";
                conditionIconURL = cObs.getIconUrl();
                Log.d("Retrofit","Weather Conditions: Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Retrofit","Weather Conditions: FAILED");
            }
        });
    }

    private void retrofitWeatherLocation() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(GEOENDPOINT)
                .build();

        WeatherLocationAPI weatherLocationAPI = restAdapter.create(WeatherLocationAPI.class);

        Log.d("Coords", "lat=" + latitude + " lon=" + longitude);

        weatherLocationAPI.getFeed(latitude, longitude, new Callback<WeatherLocation>() {

            @Override
            public void success(WeatherLocation weatherLocation, Response response) {
                Location location = weatherLocation.getLocation();
                country = location.getCountry();
                state = location.getState();
                city = location.getCity();
                zip = location.getZip();
                Log.d("RetroFit!","Success");
                retrofitWeatherConditions();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("RetroFit!","Failed");
            }
        });

    }

    private Context getContext(){
        return context;
    }

    private android.location.Location getLocation(){
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return lm.getLastKnownLocation(lm.getBestProvider(criteria, true));
    }

    /*
    sets Latitude and Longitude mVariables using Location
    if location is unavailable, gets previously saved location
     */
    private void setLatLon(android.location.Location location){
        if(location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            String filename = WEATHER_LAST_LOCATION;
            String string = "lon=" + longitude + ",lat=" + latitude;
            FileOutputStream outputStream;

            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(string.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            String[] fileList = context.fileList();
            for(String name: fileList){
                if(name.equals(WEATHER_LAST_LOCATION)){
                    String lastLoc = read_file(context, WEATHER_LAST_LOCATION);
                    Log.d("LastLoc:", lastLoc);
                    String[] coords = lastLoc.split(",");
                    longitude = Double.parseDouble(coords[0].substring(4));
                    latitude = Double.parseDouble(coords[1].substring(4));
                    break;
                }
            }
        }
    }

    public String read_file(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }

    public void bindViews(final CardAdapter.WeatherViewHolder weatherViewHolder){

                Log.d("Adapter","Binding Weather Card");
                weatherViewHolder.city.setText(city);
                weatherViewHolder.location.setText(state + " " + country + " " + zip);
                weatherViewHolder.condition.setText(weather);
                weatherViewHolder.humidity.setText("Percip " + humidity);
                if (isMetric) {
                    weatherViewHolder.temp.setText(tempC);
                    weatherViewHolder.wind.setText("Wind " + windKPH);
                } else {
                    weatherViewHolder.temp.setText(tempF);
                    weatherViewHolder.wind.setText("Wind " + windMPH);
                }
                Picasso.with(weatherViewHolder.weatherCard.getContext()).load(conditionIconURL).centerCrop().resize(250,250).into(weatherViewHolder.conditionImage);

    }

    public boolean dataIsReady(){
        return conditionsComplete & forecastComplete & locationComplete;
    }

}
