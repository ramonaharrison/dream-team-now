package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.LocationAPI;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.LocationModel;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherAPI;
import com.ramonaharrison.dev.dreamteamnow.WeatherAPI.WeatherModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by c4q-anthonyf on 6/23/15.
 */
public class WeatherInfo extends CardInfo {
    private Context context;
//http://api.wunderground.com/api/8e05118212e07ce3/US/NY/Long_Island_City.html
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

//    private AsyncCurrentWeather weatherSync;

    public WeatherInfo(Context context) {
        this.context = context.getApplicationContext();
        setType("weather");
        isMetric = false;
        setPriority(1);
        Location location = getLocation();
        setLatLon(location);
//        weatherSync = new AsyncCurrentWeather();
//        weatherSync.execute();

        //Retrofit section start from here...
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT + GEOLOOKUP + latitude + "," + longitude + ".json").build();

        LocationAPI locationAPI = restAdapter.create(LocationAPI.class);

        locationAPI.getFeed(new Callback<LocationModel>() {
            String ErrorLog = "";

            @Override
            public void success(LocationModel locationModel, Response response) {
                ErrorLog += country = locationModel.getCountry();
                ErrorLog += state = locationModel.getState();
                ErrorLog += city = locationModel.getCity();
                ErrorLog += zip = locationModel.getZip();
                ErrorLog += requestURL = locationModel.getRequesturl();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ErrorLog", ErrorLog);
                Log.d("RetroFit!","FAILED!!!");

            }
        });
    }

//    private class AsyncCurrentWeather extends AsyncTask<Void, Void, Void>{
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            Location location = getLocation();
//            setLatLon(location);
//
//            try {
//
//                //API JSON request for weather location JSON
//                URL url = new URL(ENDPOINT+GEOLOOKUP+latitude+","+longitude+".json");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//                connection.setConnectTimeout(0);
//                connection.setReadTimeout(0);
//                InputStream in = connection.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                StringBuilder builder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line + "\n");
//                }
//                String geoResponse = builder.toString();
//
//                JSONObject geoData = new JSONObject(geoResponse);
//                JSONObject weatherLoc = geoData.getJSONObject("location");
//                country = weatherLoc.getString("country");
//                state = weatherLoc.getString("state");
//                city = weatherLoc.getString("city");
//                zip = weatherLoc.getString("zip");
//
//                requestURL = weatherLoc.getString("requesturl");
//                requestURL = requestURL.replace(".html",".json");
//
//                //API JSON request for current conditions
//                URL urlConditions = new URL(ENDPOINT + FORECAST + requestURL);
//                HttpURLConnection connectionC = (HttpURLConnection) urlConditions.openConnection();
//                connectionC.setConnectTimeout(0);
//                connectionC.setReadTimeout(0);
//                InputStream inC = connectionC.getInputStream();
//                BufferedReader readerC = new BufferedReader(new InputStreamReader(inC));
//                StringBuilder builderC = new StringBuilder();
//                String lineC;
//                while ((lineC = readerC.readLine()) != null) {
//                    builderC.append(lineC + "\n");
//                }
//                String conditionJSON = builderC.toString();
//
//                JSONObject conditionData = new JSONObject(conditionJSON);
////                Log.d("conditionJSON:",conditionJSON);
//                JSONObject currentObs = conditionData.getJSONObject("response");
//
//                tempF = currentObs.getString("tempf");
//                tempC = currentObs.getString("tempc");
//                weather = currentObs.getString("condition");
//                humidity = currentObs.getString("relative_humidity");
//                windMPH = currentObs.getDouble("wind_mph") + "";
//                windKPH = currentObs.getDouble("wind_kpm") + "";
//                conditionIconURL = currentObs.getString("icon_url");
//
//                //API JSON request for weather forecast
//                URL urlForecast = new URL(ENDPOINT + FORECAST + requestURL);
//                HttpURLConnection connectionFC = (HttpURLConnection) urlForecast.openConnection();
//                connectionFC.setConnectTimeout(0);
//                connectionFC.setReadTimeout(0);
//                InputStream inFC = connectionFC.getInputStream();
//                BufferedReader readerFC = new BufferedReader(new InputStreamReader(inFC));
//                StringBuilder builderFC = new StringBuilder();
//                String lineFC;
//                while ((lineFC = readerFC.readLine()) != null) {
//                    builderFC.append(lineFC + "\n");
//                }
//                String forecastJSON = builderFC.toString();
//                JSONObject forecastData = new JSONObject(forecastJSON);
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//    }
//
//    public void destroyWeatherAsync(){
//        if(!weatherSync.isCancelled())
//            weatherSync.cancel(true);
//    }

    private Location getLocation(){
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return lm.getLastKnownLocation(lm.getBestProvider(criteria, true));
    }

    private void setLatLon(Location location){
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
                wvh.location.setText(city+ ", " + state + ", " + country + ", " + zip);
                wvh.condition.setText(weather);
                wvh.humidity.setText("Percip " + humidity + "%");
                if(isMetric) {
                    wvh.temp.setText(tempC+"°");
                    wvh.wind.setText("Wind " + windMPH + "kph");
                }
                else {
                    wvh.temp.setText(tempF+"°");
                    wvh.wind.setText("Wind " + windMPH + "mph");
                }
                Picasso.with(wvh.weatherCard.getContext()).load(conditionIconURL).centerCrop().resize(200,200).into(wvh.conditionImage);
            }
        };

//        if(weatherSync.getStatus() == AsyncTask.Status.RUNNING){
            new Handler().post(runnable);
//        }else{
//            new Handler().post(runnable);
//        }


    }
}
