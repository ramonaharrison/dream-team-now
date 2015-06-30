package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Handler;
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
public class WeatherInfo extends CardInfo {
    private final Context context;
    private static final String API_KEY = "8e05118212e07ce3";
    private static final String WEATHER_LAST_LOCATION = "weather_last_location.txt";
    private static final String ENDPOINT = "http://api.wunderground.com/api/" + API_KEY;
    private static final String GEOLOOKUP = "/geolookup/q/";
    private static final String FORECAST = "/forecast/q/";
    private static final String CONDITIONS = "/conditions/q/";
    private String requestURL;

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

    public WeatherInfo(Context context) {
        this.context = context.getApplicationContext();
        setType("weather");
        isMetric = false;
        setPriority(1);
        android.location.Location weather = getLocation();
        setLatLon(weather);

        //Retrofit section start from here...

        retrofitWeatherLocation();
    }

    private void retrofitWeatherConditions() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        WeatherConditionsAPI weatherConditionsAPI = restAdapter.create(WeatherConditionsAPI.class);

        weatherConditionsAPI.getFeed(country.replace(" ","_"), state.replace(" ", "_"), city.replace(" ","_"), new Callback<WeatherConditions>() {

            @Override
            public void success(WeatherConditions weatherConditions, Response response) {
                CurrentObservation cObs = weatherConditions.getCurrentObservation();

                tempC = cObs.getTempC() + "°";
                tempF = cObs.getTempF() + "°";
                weather = cObs.getWeather();
                humidity = cObs.getRelativeHumidity();
                windMPH = cObs.getWindMph() + "mph";
                windKPH = cObs.getWindKph() + "kph";
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
                .setEndpoint(ENDPOINT)
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
                requestURL = location.getRequesturl();
                requestURL = requestURL.replace(".html",".json");
                Log.d("REQUESTURL",requestURL + "");
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
        Runnable runnable = new Runnable() {
            CardAdapter.WeatherViewHolder wvh = weatherViewHolder;
            @Override
            public void run() {
                Log.d("Adapter","Binding Weather Card");
                wvh.location.setText(city + ", " + state + ", " + country + ", " + zip);
                wvh.condition.setText(weather);
                wvh.humidity.setText("Percip " + humidity);
                if (isMetric) {
                    wvh.temp.setText(tempC);
                    wvh.wind.setText("Wind " + windMPH);
                } else {
                    wvh.temp.setText(tempF);
                    wvh.wind.setText("Wind " + windMPH);
                }
                Picasso.with(wvh.weatherCard.getContext()).load(conditionIconURL).centerCrop().resize(200, 200).into(wvh.conditionImage);
            }
        };
            new Handler().post(runnable);
    }
}
