package com.ramonaharrison.dev.dreamteamnow;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

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
        protected TextView name;
        protected TextView description;
        protected TextView time;
        protected TextView location;
        protected TextView minutesBefore;
        protected CardView todoCard;

        public TodoViewHolder(View v) {
            super(v);
            name =  (TextView) v.findViewById(R.id.name);
            description = (TextView)  v.findViewById(R.id.description);
            time = (TextView)  v.findViewById(R.id.time);
            location = (TextView) v.findViewById(R.id.location);
            minutesBefore = (TextView) v.findViewById(R.id.minutes_before);
            todoCard = (CardView) v.findViewById(R.id.todo_card_view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        // TODO: add an if statement/int value for each card type
        if (cardList.get(position).getType().equals("todo")) {
            return TODO;
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

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        // TODO: add an if statement for each card type

        if (viewHolder.getItemViewType() == TODO) {
            TodoInfo todo = (TodoInfo) cardList.get(position);
            TodoViewHolder todoViewHolder = (TodoViewHolder) viewHolder;
            todoViewHolder.name.setText(todo.getName());
            todoViewHolder.description.setText(todo.getDescription());
            todoViewHolder.time.setText(todo.getWhenString());
            todoViewHolder.location.setText(todo.getWhereString());
            todoViewHolder.minutesBefore.setText(todo.getMinutesBeforeString());
            setAnimation(todoViewHolder.todoCard, position);
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


