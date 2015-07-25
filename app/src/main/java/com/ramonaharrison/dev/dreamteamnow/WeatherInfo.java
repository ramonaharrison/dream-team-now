package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Handler;
import android.util.Log;

import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.Currently;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.Daily;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.Datum__;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.WeatherConditions;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherConditions.WeatherConditionsAPI;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation.Location;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation.WeatherLocation;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherLocation.WeatherLocationAPI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by c4q-anthonyf on 6/23/15.
 */

    //TODO: Feature: add new weather cards based on city
    //TODO: 5 day forecast
    //TODO: use cache to store previously searched location info as to not query API as often

public class WeatherInfo extends CardInfo {
    private final Context context;
    private static final String OWM_KEY = "8e05118212e07ce3";
    private static final String WEATHER_LAST_LOCATION = "weather_last_location.txt";
    private static final String GEO_ENDPOINT = "http://api.wunderground.com/api/" + OWM_KEY;
    public static final String GEOLOOKUP = "/geolookup/q/";
    public static final String CONDITIONS = "/conditions/q/";

    public static final String FC_KEY = "23df8d08609dc3723424be5528a791f6";
    public static final String FORECAST_EP = "https://api.forecast.io/forecast/" + FC_KEY;

    private static final String ICONSEARCH_EP = "https://ajax.googleapis.com/ajax/services/search";
    private static final String ICONSEARCH_APPEND = "iconarchive.com%20weather%20icon.png";


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
    private String iconSummary;
    private boolean isMetric;

    //data for 5 day forecast
    private Calendar calendar;
    private String[] highF;
    private String[] highC;
    private String[] lowF;
    private String[] lowC;
    private String[] summaries;
    private String[] iconSummaries;
    private final String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    //data booleans
    private boolean conditionsComplete;
    private boolean locationComplete;
    private boolean iconComplete;



    public WeatherInfo(Context context) {
        this.context = context.getApplicationContext();
        setType("weather");
        setPriority(1);
        initializeData();

        android.location.Location weather = getLocation();
        setLatLon(weather);

        //Retrofit section start from here...

        retrofitWeatherLocation();
    }

    private void initializeData(){
        this.calendar = Calendar.getInstance();
        isMetric = false;
        conditionsComplete = false;
        locationComplete = false;
        iconComplete = false;

        highF = new String[5];
        highC = new String[5];
        lowF = new String[5];
        lowC = new String[5];
        summaries = new String[5];
        iconSummaries = new String[5];

        longitude = -74.0059;
        latitude = 40.7127;
    }

    private void retrofitWeatherConditions() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FORECAST_EP)
                .build();

        WeatherConditionsAPI weatherConditionsAPI = restAdapter.create(WeatherConditionsAPI.class);

        weatherConditionsAPI.getFeed(latitude, longitude, new Callback<WeatherConditions>() {

            @Override
            public void success(WeatherConditions weatherConditions, Response response) {
                Currently currently = weatherConditions.getCurrently();

                double temp = Math.round(currently.getTemperature());

                tempC = ((int)((temp - 32) * (5/9))) + "°";
                tempF = ((int) temp) + "°";
                weather = currently.getSummary();
                humidity = ((int)(currently.getHumidity() * 100)) + "%";
                windMPH = currently.getWindSpeed().intValue() + " mph";
                windKPH = ((int)(currently.getWindSpeed() * 1.60934)) + " kph";
                iconSummary = currently.getIcon();

                Daily daily = weatherConditions.getDaily();
                List<Datum__> data = daily.getData();

                for(int i = 0; i < 5; i++){
                    double high = Math.round(data.get(i).getTemperatureMax());
                    highF[i] = ((int) high) + "°";
                    highC[i] = ((int) ((high - 32) * (5/9) )) + "°";

                    double low = Math.round(data.get(i).getTemperatureMin());
                    lowF[i] = ((int) low) + "°";
                    lowC[i] = ((int) ((low - 32) * (5/9) )) + "°";

                    summaries[i] = data.get(i).getSummary();
                    iconSummaries[i] = data.get(i).getIcon();

                }

                conditionsComplete = true;
                Log.d("Retrofit","Weather Conditions: Success");
            }

            @Override
            public void failure(RetrofitError error) {
                conditionsComplete = true;
                Log.d("Retrofit","Weather Conditions: FAILED");
            }
        });
    }

    private void retrofitWeatherLocation() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(GEO_ENDPOINT)
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
                Log.d("RetroFit - Weather","Success");
                retrofitWeatherConditions();
                locationComplete = true;
            }

            @Override
            public void failure(RetrofitError error) {
                locationComplete = true;
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
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
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

    public void bindViews(final CardAdapter.WeatherViewHolder wvh){
        Runnable bindViews = new Runnable() {
            @Override
            public void run() {
                Log.d("Adapter","Binding Weather Card");
                wvh.city.setText(city);
                wvh.location.setText(state + " " + country + " " + zip);
                wvh.condition.setText(weather);
                wvh.humidity.setText("Percip " + humidity);
                if (isMetric) {
                    wvh.temp.setText(tempC);
                    wvh.wind.setText("Wind " + windKPH);
                } else {
                    wvh.temp.setText(tempF);
                    wvh.wind.setText("Wind " + windMPH);
                }

                wvh.conditionImage.setImageResource(getIconResource(iconSummary));

                int currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;

                wvh.day1.setText(days[currentDay]);
                wvh.day2.setText(days[(currentDay + 1) % 7]);
                wvh.day3.setText(days[(currentDay + 2) % 7]);
                wvh.day4.setText(days[(currentDay + 3) % 7]);
                wvh.day5.setText(days[(currentDay + 4) % 7]);

                wvh.condition1.setImageResource(getIconResource(iconSummaries[0]));
                wvh.condition2.setImageResource(getIconResource(iconSummaries[1]));
                wvh.condition3.setImageResource(getIconResource(iconSummaries[2]));
                wvh.condition4.setImageResource(getIconResource(iconSummaries[3]));
                wvh.condition5.setImageResource(getIconResource(iconSummaries[4]));

                if(isMetric){
                    wvh.maxTemp1.setText(highC[0]);
                    wvh.maxTemp2.setText(highC[1]);
                    wvh.maxTemp3.setText(highC[2]);
                    wvh.maxTemp4.setText(highC[3]);
                    wvh.maxTemp5.setText(highC[4]);

                    wvh.minTemp1.setText(lowC[0]);
                    wvh.minTemp2.setText(lowC[1]);
                    wvh.minTemp3.setText(lowC[2]);
                    wvh.minTemp4.setText(lowC[3]);
                    wvh.minTemp5.setText(lowC[4]);
                }else {
                    wvh.maxTemp1.setText(highF[0]);
                    wvh.maxTemp2.setText(highF[1]);
                    wvh.maxTemp3.setText(highF[2]);
                    wvh.maxTemp4.setText(highF[3]);
                    wvh.maxTemp5.setText(highF[4]);

                    wvh.minTemp1.setText(lowF[0]);
                    wvh.minTemp2.setText(lowF[1]);
                    wvh.minTemp3.setText(lowF[2]);
                    wvh.minTemp4.setText(lowF[3]);
                    wvh.minTemp5.setText(lowF[4]);
                }



            }
        };

        new Handler().postDelayed(bindViews,3000);

    }

    private int getIconResource(String iconSummary){
        switch (iconSummary){
            case "cloudy":
                return R.drawable.cloudy;
            case "partly-cloudy-day":
                return R.drawable.cloudy_day;
            case "partly-cloudy-night":
                return R.drawable.cloudy_night;
            case "clear-day":
                return R.drawable.clear_day;
            case "clear-night":
                return R.drawable.cloudy_night;
            case "rain":
                return R.drawable.rain;
            case "snow":
                return R.drawable.snow;
            case "sleet":
                return R.drawable.sleet;
            case "wind":
                return R.drawable.wind;
            case "fog":
                return R.drawable.fog;
            case "thunderstorm":
                return R.drawable.thunderstorm;
            case "hail":
                return R.drawable.hail;
            default:
                return R.drawable.clear_day;
        }
    }

    private boolean dataIsReady(){
        return conditionsComplete & iconComplete & locationComplete;
    }

}
