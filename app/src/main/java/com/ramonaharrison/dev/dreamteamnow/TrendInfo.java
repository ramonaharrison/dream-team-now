package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.ramonaharrison.dev.dreamteamnow.model.Result;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kadeemmaragh on 6/27/15.
 */
public class TrendInfo extends CardInfo {

    List<Result> newsStories = new ArrayList<>();


    public TrendInfo(){

        setType("trend");

        Result res = new Result();
        res.setTitle("THE WORLD IS BURNING");
        res.setSection("USA");
        newsStories.add(res);
        newsStories.add(res);

        new AsyncNews().execute();
    }

    // TODO: Refresh Trend Card on CardAdapter


    public List<Result> getNewsStories(){
        return newsStories;
    }

    public void loadImage(Context context, ImageView imgView, int num){
        String url = getNewsStories().get(num).getThumbnailStandard();

        try{
            Picasso.with(context).load(url).into(imgView);
        }
        catch(Exception e){
            Picasso.with(context).load(R.drawable.img_unavailable).into(imgView);

        }

    }

//TODO: Switch back to Retrofit

    public class AsyncNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String newsUrl = "http://api.nytimes.com/svc/news/v3/content/nyt/all/24?api-key=1fb09ee32a69fd6c40f98e7e38f6b0a4:6:72391617";
            List<Result> newsList = new ArrayList<>();

            //TODO: JSON response doesn't provide proper punctuation. Ex. Instead of a apostrophe, it will return &#82343(etc.). Might be able to fix before saving to string.
            try {
                URL url = new URL(newsUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                String output = readStream(connection.getInputStream());

                JSONObject fullWebPage = new JSONObject(output);
                JSONArray arr = fullWebPage.getJSONArray("results");

                for (int i = 0; i < arr.length(); i++){
                    Result result = new Result();

                    JSONObject arr2 = arr.getJSONObject(i);

                    result.setSection(arr2.getString("section"));
                    result.setTitle(arr2.getString("title"));
                    result.setAbstract(arr2.getString("abstract"));
                    result.setUrl(arr2.getString("url"));
                    result.setThumbnailStandard(arr2.getString("thumbnail_standard"));

//                    newsStories.add(result);

                    newsList.add(result);
                }
                newsStories = newsList;
            }
            catch(Exception e){
                Log.d("json", "" + e.getMessage());
            }

            return null;
        }

    }

    private String readStream(InputStream in) throws IOException {
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(in, "UTF8");
        StringWriter writer = new StringWriter();
        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }
        return writer.toString();
    }

}
