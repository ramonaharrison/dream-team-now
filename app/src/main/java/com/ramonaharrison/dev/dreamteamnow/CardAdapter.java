package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private final int MAP = 692;

    public CardAdapter(List<CardInfo> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
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

    // TODO: create a holder class for each card type
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

    @Override
    public int getItemViewType(int position) {

        // TODO: add an if statement/int value for each card type
        if (cardList.get(position).getType().equals("todo")) {
            return TODO;
        } else if (cardList.get(position).getType().equals("map")) {
            return MAP;
        }
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
         }

        if (viewType == MAP) {
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.map_card, parent, false);

            return new MapViewHolder(itemView);
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
            todoViewHolder.time.setText(todo.getTimeString());
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

        } else if (viewHolder.getItemViewType() == MAP) {

            MapViewHolder mapViewHolder = (MapViewHolder) viewHolder;
            MapInfo map = (MapInfo) cardList.get(position);
            mapViewHolder.name.setText(map.getName());
            mapViewHolder.openMaps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            setAnimation(mapViewHolder.mapCard, position);

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

 }


