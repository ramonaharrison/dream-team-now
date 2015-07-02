package com.ramonaharrison.dev.dreamteamnow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Doc;
import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Result;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List news = new ArrayList<>(0);

    // add menu item to close activity/go back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getBundleFromIntent();
        initializeRecyclerView();
        setAdapter();

    }

    private void getBundleFromIntent(){

        if(getIntent().getExtras().containsKey("news")) {
            Object obj = getIntent().getExtras().get("news");
            if (obj != null) {
                news = (List<Result>) obj;
            }
        }
        else if(getIntent().getExtras().containsKey("query")){
            Object obj = getIntent().getExtras().get("query");
            if (obj != null) {
                news = (List<Doc>) obj;
            }
        }
    }

    private void initializeRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.newsList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter() {
        newsAdapter = new NewsAdapter(news, getApplicationContext());
        recyclerView.setAdapter(newsAdapter);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_search){
            Intent searchNewsIntent = new Intent(this, NewsSearchActivity.class);
            startActivity(searchNewsIntent);
            overridePendingTransition(R.anim.slide_up_from_bottom, R.anim.slide_down_from_top);
        }
        if (id == R.id.action_close) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
