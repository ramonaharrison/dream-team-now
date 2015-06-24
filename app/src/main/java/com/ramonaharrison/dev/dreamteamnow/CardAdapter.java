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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CardInfo> cardList;
    private int lastPosition = -1;
    Context context;

    public CardAdapter(List<CardInfo> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {

        if (cardList.get(i).getType().equals("todo")) {
            TodoViewHolder todoViewHolder = (TodoViewHolder) cardViewHolder;
            TodoInfo todo = (TodoInfo) cardList.get(i);
            todoViewHolder.name.setText(todo.getName());
            todoViewHolder.description.setText(todo.getDescription());
            todoViewHolder.time.setText(todo.getWhenString());
            todoViewHolder.location.setText(todo.getWhereString());
            todoViewHolder.minutesBefore.setText("" + todo.getMinutesBefore());
            setAnimation(todoViewHolder.todoCard, i);
        }

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.todo_card, viewGroup, false);

        return new TodoViewHolder(itemView);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(View v) {
            super(v);
        }
    }

    public class TodoViewHolder extends CardViewHolder {
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

}

