package com.ramonaharrison.dev.dreamteamnow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private List<CardInfo> cards;
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecyclerView();
        initializeCards();
        setAdapter();
    }

    private void initializeCards() {
        //TODO: add your cards to the deck here
        cards = new ArrayList<>();
        initializeTodo();
        cards.add(new WeatherInfo(getApplicationContext()));
        cards.add(new WeatherInfo(getApplicationContext()));
        sortCardList(cards);

    }

    private void initializeTodo() {
        calendar = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            TodoInfo todo = new TodoInfo("Sample", "This is the description", calendar, true, 30);
            cards.add(todo);
        }
    }

    private void initializeRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter() {
        CardAdapter cAdapter = new CardAdapter(cards, getApplicationContext());
        recyclerView.setAdapter(cAdapter);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();
    }

    //uses insertion sort algorithm to sort cards based on priority
    public void sortCardList(List<CardInfo> cardList){
        for(int i = 1; i < cardList.size(); i++){
            int currentPriority = cardList.get(i).getPriority();
            int previousPriority = cardList.get(i-1).getPriority();
            Log.d("Priorities:","current: " + currentPriority + ", prev: " + previousPriority);
            if(currentPriority < previousPriority){
                int j = 1;
                for(;i-j >= 0; j++){
                    int priorityToCheck = cards.get(i-j).getPriority();
                    if(!(currentPriority < priorityToCheck)){
                        break;
                    }
                }
                CardInfo cardInfo = cards.remove(i);
                cards.add(i-(j-1),cardInfo);
            }
        }
    }

}
