package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ramonaharrison.dev.dreamteamnow.NYTAPI.NYTModel;
import com.ramonaharrison.dev.dreamteamnow.NYTAPI.NYTapi;
import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kadeemmaragh on 6/27/15.
 */
public class TrendInfo extends CardInfo {

    final String NEWSWIRE_URL = "http://api.nytimes.com/svc/news/v3/content/nyt/all";
    List<Result> newsStories = new ArrayList<>();


    public TrendInfo() {
        setType("trend");
        setPriority(2);
        getNewsData();
    }

    public List<Result> getNewsStories() {
        return newsStories;
    }

    public void loadImage(Context context, ImageView imgView, int num) {
        String url = getNewsStories().get(num).getThumbnailStandard();
        try {
            Picasso.with(context).load(url).into(imgView);
        } catch (Exception e) {
            Picasso.with(context).load(R.drawable.img_unavailable).into(imgView);
        }
    }

public void getNewsData(){

    RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(NEWSWIRE_URL).build();

    NYTapi nyt = restAdapter.create(NYTapi.class);

    nyt.getFeed(new Callback<NYTModel>() {
        @Override
        public void success(NYTModel nytModel, Response response) {

            newsStories = nytModel.getResults();
            Log.d("retroSuccess", "URL: " + response.getUrl());

        }

        @Override
        public void failure(RetrofitError error) {
            Log.d("retroFAIL", "URL: " + error.getUrl() + "||| Error: " + error.toString());

        }
    });
}

    public void setFields(final CardAdapter.TrendingViewHolder trendingViewHolder, final Context context) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    trendingViewHolder.section.setText(getNewsStories().get(0).getSection());
                    trendingViewHolder.title.setText(getNewsStories().get(0).getTitle());
                    loadImage(context, trendingViewHolder.thumbnail, 0);

                    trendingViewHolder.section2.setText(getNewsStories().get(1).getSection());
                    trendingViewHolder.title2.setText(getNewsStories().get(1).getTitle());
                    loadImage(context, trendingViewHolder.thumbnail2, 1);
                    trendingViewHolder.progress.setVisibility(View.GONE);
                }
                catch(Exception e){
                    trendingViewHolder.section.setText("Error");
                    trendingViewHolder.title.setText("Could not load content");

                    trendingViewHolder.section2.setText("Error");
                    trendingViewHolder.title2.setText("Could not load content");
                    trendingViewHolder.progress.setVisibility(View.GONE);
                }

            }
        };
        new Handler().postDelayed(runnable,3000);
    }

}
