package com.ramonaharrison.dev.dreamteamnow;

import android.os.AsyncTask;
import android.util.Log;

import com.ramonaharrison.dev.dreamteamnow.model.Result;

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

    String section;
    String title;
    List<Result> newsStories = new ArrayList<>();

    public TrendInfo(){


        setType("trend");
        Result res = new Result();
        res.setTitle("THE WORLD");
        res.setSection("USA");
        newsStories.add(res);

        new AsyncNews().execute();
    }

   public TrendInfo(String section, String title){
       setType("trend");
       this.section = section;
       this.title = title;

   }

    public String getSection(){
        return section;
    }

    public String getTitle(){
        return title;
    }

    public List<Result> getNewsStories(){
        return newsStories;
    }

    public class AsyncNews extends AsyncTask<Void, Void, List<Result>> {

        @Override
        protected List<Result> doInBackground(Void... params) {

            String newsUrl = "http://api.nytimes.com/svc/news/v3/content/nyt/all/24?api-key=1fb09ee32a69fd6c40f98e7e38f6b0a4:6:72391617";
            List<Result> newsList = new ArrayList<>();

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

                    newsList.add(result);
                }
            }
            catch(Exception e){
                Log.d("json", "" + e.getStackTrace().toString());
            }

            return newsList;
        }

        @Override
        protected void onPostExecute(List<Result> list) {
            super.onPostExecute(list);
            //Update list here
            newsStories = list;
            Log.d("asyncTas", "Post execute");




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
