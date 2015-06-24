package com.ramonaharrison.dev.dreamteamnow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

}
