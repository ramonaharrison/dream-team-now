package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;

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


/**
 * Created by c4q-anthonyf on 6/23/15.
 */
public class WeatherInfo extends CardInfo {

    private static final String API_KEY = "&APPID=e745be7dec5ece10d31a8fa4f48a8649";
    private static final String WEATHER_LAST_LOCATION = "weather_last_location.txt";
    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/weather?";
    private String JSONString;
    private double longitude;
    private double latitude;
    private Context context;

    public WeatherInfo(Context context){
        this.context = context.getApplicationContext();
        setType("weather");
        setPriority(1);
        Location location = getLocation();
        setLongLat(location);
        setJSON();
    }

    private void setJSON() {
        new AsyncWeatherJSON().execute();
    }

    private class AsyncWeatherJSON extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            int lat = (int) latitude;
            int lon = (int) longitude;
            try {
                URL url = new URL(ENDPOINT +"lat="+lat+"&lon="+lon+API_KEY);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setConnectTimeout(0);
                connection.setReadTimeout(0);
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                JSONString = builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private Location getLocation(){
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return lm.getLastKnownLocation(lm.getBestProvider(criteria, true));
    }

    private void setLongLat(Location location){
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
                    String lastLoc = read_file(context,WEATHER_LAST_LOCATION);
                    Log.d("LastLoc:", lastLoc);
                    String[] coords = lastLoc.split(",");
                    longitude = Double.parseDouble(coords[0].substring(4));
                    latitude = Double.parseDouble(coords[1].substring(4));
                    break;
                }
            }
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
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
}
