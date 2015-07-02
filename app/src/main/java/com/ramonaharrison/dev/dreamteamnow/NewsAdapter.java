package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Doc;
import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kadeemmaragh on 6/29/15.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List newsList;
    private Context context;

    public NewsAdapter(List newsList, Context context){
        this.newsList = newsList;
        this.context = context;
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        protected TextView storyTitle, storyDescription;
        protected ImageView storyImage;

        public NewsViewHolder(View v) {
            super(v);

            storyTitle = (TextView) v.findViewById(R.id.newsStoryTitle);
            storyDescription = (TextView) v.findViewById(R.id.newsStoryDescription);
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

        if(newsList.get(position).getClass() == Result.class) {
            Result newsStory = (Result) newsList.get(position);
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.storyTitle.setText(newsStory.getTitle());
            newsViewHolder.storyDescription.setText(newsStory.getAbstract());
            loadImage(context, newsStory.getThumbnailStandard(), newsViewHolder.storyImage);
            newsViewHolder.itemView.setOnClickListener(new CustomViewListener(newsStory.getUrl()
            ));
        }else if(newsList.get(position).getClass() == Doc.class) {
            Doc newsStory = (Doc) newsList.get(position);
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.storyTitle.setText(newsStory.getHeadline().getMain());
            newsViewHolder.storyDescription.setText(newsStory.getAbstract());
            newsViewHolder.itemView.setOnClickListener(new CustomViewListener(newsStory.getWebUrl()));
        }


    }

    //Handles loading picture into imgView.
    public void loadImage(Context context, String imageLink, ImageView img){
        try{
            Picasso.with(context).load(imageLink).into(img);
        }
        catch(Exception e){
            Picasso.with(context).load(R.drawable.img_unavailable).into(img);

        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


public class CustomViewListener implements View.OnClickListener {

    String url;
    public CustomViewListener(String url){

        this.url = url;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}


}
