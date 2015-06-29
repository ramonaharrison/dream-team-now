package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramonaharrison.dev.dreamteamnow.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kadeemmaragh on 6/29/15.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> newsList;
    private Context context;

    public NewsAdapter(List<Result> newsList, Context context){
        this.newsList = newsList;
        this.context = context;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        protected TextView storyTitle, storyDescription, storyTime;
        protected ImageView storyImage;

        public NewsViewHolder(View v) {
            super(v);

            storyTitle = (TextView) v.findViewById(R.id.newsStoryTitle);
            storyDescription = (TextView) v.findViewById(R.id.newsStoryDescription);
            storyTime = (TextView)v.findViewById(R.id.newsStoryTime);
            storyImage = (ImageView) v.findViewById(R.id.newsStoryImage);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.news_row, parent, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Result newsStory = newsList.get(position);
        NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
        newsViewHolder.storyTitle.setText(newsStory.getTitle());
        newsViewHolder.storyDescription.setText(newsStory.getAbstract());
        loadImage(context, newsStory.getThumbnailStandard(), newsViewHolder.storyImage);

    }

    public void loadImage(Context context, String imageLink, ImageView img){
//
//        Picasso.with(context).load(newsStory.getThumbnailStandard()).into(newsViewHolder.storyImage);
        try{
            Picasso.with(context).load(imageLink).into(img);
        }
        catch(Exception e){
           String url = "http://static01.nyt.com/images/2015/06/29/travel/29WTGNow-Asheville-1/29WTGNow-Asheville-1-thumbStandard.jpg";
            Picasso.with(context).load(url).into(img);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
