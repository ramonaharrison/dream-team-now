package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ramonaharrison.dev.dreamteamnow.db.SQLController;

import java.util.List;

/**
 * Created by Ramona Harrison
 * on 6/23/15.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CardInfo> cardList;
    private int lastPosition = -1;
    private Context context;

    // TODO: add an int value for each card type
    private final int TODO = 1;
    private final int WEATHER = 2;
    private final int MAP = 692;
    private final int TREND = 11;

    private View mapItemView;

    public CardAdapter(List<CardInfo> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }

    public CardAdapter(List<CardInfo> cardList, Context context, View mapItemView){
        this(cardList, context);
        this.mapItemView = mapItemView;
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }

    // TODO: create a holder class for each card type
    public class TodoViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout label;
        protected TextView name;
        protected TextView time;
        protected TextView location;
        protected TextView minutesBefore;
        protected CardView todoCard;
        protected Button editTodo;
        protected Button dismissTodo;

        public TodoViewHolder(View v) {
            super(v);
            label = (LinearLayout) v.findViewById(R.id.labelTop);
            name =  (TextView) v.findViewById(R.id.name);
            time = (TextView)  v.findViewById(R.id.time);
            location = (TextView) v.findViewById(R.id.location);
            minutesBefore = (TextView) v.findViewById(R.id.minutes_before);
            todoCard = (CardView) v.findViewById(R.id.todo_card_view);
            editTodo = (Button) v.findViewById(R.id.edit_icon);
            dismissTodo = (Button) v.findViewById(R.id.dismiss_icon);
        }
    }

    public class MapViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout label;
        protected TextView name;
        protected CardView mapCard;
        protected Button openMaps;

        public MapViewHolder(View v) {
            super(v);
            label = (LinearLayout) v.findViewById(R.id.mapLabelTop);
            name =  (TextView) v.findViewById(R.id.mapName);
            mapCard = (CardView) v.findViewById(R.id.map_card_view);
            openMaps = (Button) v.findViewById(R.id.map_edit_icon);
        }
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        protected CardView weatherCard;
        protected TextView location;
        protected TextView city;
        protected TextView condition;
        protected TextView wind;
        protected TextView humidity;
        protected ImageView conditionImage;
        protected TextView temp;

        public WeatherViewHolder(View v) {
            super(v);
            weatherCard = (CardView) v.findViewById(R.id.weather_card_view);
            city = (TextView) v.findViewById(R.id.city);
            location = (TextView) v.findViewById(R.id.location);
            condition = (TextView) v.findViewById(R.id.condition);
            wind = (TextView) v.findViewById(R.id.wind);
            humidity = (TextView) v.findViewById(R.id.humidity);
            conditionImage = (ImageView) v.findViewById(R.id.condition_image);
            temp = (TextView) v.findViewById(R.id.temp);
        }
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder{

        TextView title,section,title2,section2;
        CardView trendCard;
        ImageView thumbnail, thumbnail2;
        Button moreButton;
        ProgressBar progress;

        public TrendingViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.newsTitle);
            section = (TextView) v.findViewById(R.id.newsSection);
            thumbnail = (ImageView) v.findViewById(R.id.newsThumbnail);

            title2 = (TextView) v.findViewById(R.id.newsTitleSecond);
            section2 = (TextView) v.findViewById(R.id.newsSectionSecond);
            thumbnail2 = (ImageView) v.findViewById(R.id.newsThumbnailSecond);

            progress = (ProgressBar) v.findViewById(R.id.progressBarTrend);
            moreButton = (Button) v.findViewById(R.id.moreBtn);
            trendCard = (CardView) v.findViewById(R.id.trend_card_view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // TODO: add an if statement/int value for each card type
        if(cardList.get(position).getType().equals("todo")){
            return TODO;
        }else if(cardList.get(position).getType().equals("weather")){
            return WEATHER;
        } else if (cardList.get(position).getType().equals("map")) {
            return MAP;
        }
        else if(cardList.get(position).getType().equals("trend"))
            return TREND;

        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         // TODO: add an if statement for each card type
         if (viewType == TODO) {
             View itemView = LayoutInflater.
                     from(parent.getContext()).
                     inflate(R.layout.todo_card, parent, false);

             return new TodoViewHolder(itemView);
         }else if(viewType == WEATHER){
             View itemView = LayoutInflater.
                     from(parent.getContext()).
                     inflate(R.layout.weather_card, parent, false);

             return new WeatherViewHolder(itemView);
         }
        else if(viewType == TREND){
             View itemView = LayoutInflater.
                     from(parent.getContext()).
                     inflate(R.layout.trending_card, parent, false);

             return new TrendingViewHolder(itemView);
         }

        if (viewType == MAP) {
            if(mapItemView == null){
                View itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.map_card, parent, false);
                MainActivity.setMapItemView(itemView);

                return new MapViewHolder(itemView);
            }else{
                return new MapViewHolder(mapItemView);
            }

        }

        return null;
    }

    @Override

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        // TODO: add an if statement for each card type
        if (viewHolder.getItemViewType() == TODO) {
            TodoInfo todo = (TodoInfo) cardList.get(position);
            TodoViewHolder todoViewHolder = (TodoViewHolder) viewHolder;
            todoViewHolder.name.setText(todo.getName());

            if (!todo.getTimeString().contains("-1")) {
                todoViewHolder.time.setText(todo.getTimeString());
            }

            todoViewHolder.location.setText(todo.getLocationString());
            todoViewHolder.minutesBefore.setText(todo.getMinutesBeforeString());

            todoViewHolder.editTodo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id = ((TodoInfo) cardList.get(position)).getId();
                    Intent intent = new Intent(v.getContext(), TodoActivity.class);
                    intent.putExtra("id", id);
                    v.getContext().startActivity(intent);
                }
            });

            todoViewHolder.dismissTodo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLController dbController = new SQLController(context);
                    dbController.open();
                    long id = ((TodoInfo) cardList.get(position)).getId();
                    dbController.delete(id);
                    dbController.close();
                    cardList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cardList.size());

                }
            });

            setAnimation(todoViewHolder.todoCard, position);
        }else if (viewHolder.getItemViewType() == WEATHER) {
            WeatherInfo weather = (WeatherInfo) cardList.get(position);
            WeatherViewHolder weatherViewHolder = (WeatherViewHolder) viewHolder;
            weather.bindViews(weatherViewHolder);
            setAnimation(weatherViewHolder.weatherCard, position);

        } else if (viewHolder.getItemViewType() == MAP) {

            MapViewHolder mapViewHolder = (MapViewHolder) viewHolder;
            MapInfo map = (MapInfo) cardList.get(position);
            mapViewHolder.name.setText(map.getName());
            final double lat = map.getLatitude();
            final double lon = map.getLongitude();

            mapViewHolder.openMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps"));
                    v.getContext().startActivity(intent);
                }
            });

            setAnimation(mapViewHolder.mapCard, position);
        }
        else if (viewHolder.getItemViewType() == TREND) {
            TrendInfo trendInfo = (TrendInfo) cardList.get(position);
            TrendingViewHolder trendViewHolder = (TrendingViewHolder) viewHolder;
            trendViewHolder.progress.setVisibility(View.VISIBLE);
            trendInfo.setFields(trendViewHolder, context);
            setAnimation(trendViewHolder.trendCard, position);
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    /* Methods for ItemTouchHelper.
    *  -removeItem for swiping
    *  -moveItems for drag&drop
    */
    //TODO: Remove item doesn't update the list anymore.
    public void removeItem(int position) {
        cardList.remove(position);
        notifyItemRemoved(position);
    }

    public void moveItems(int startPos, int currentPos){
        CardInfo hold = cardList.get(startPos);
        cardList.remove(startPos);
        cardList.add(currentPos,hold);
        notifyItemMoved(startPos, currentPos);

    }

 }


