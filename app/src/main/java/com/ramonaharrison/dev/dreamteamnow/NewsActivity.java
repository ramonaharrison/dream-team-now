package com.ramonaharrison.dev.dreamteamnow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.ramonaharrison.dev.dreamteamnow.model.Result;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends Activity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<Result> news = new ArrayList<>(0);

    //TODO: Get newsList from intent,
    // get imageNotfound image for xml.
    // add menu item to close activity/go back
    // setonItemClick to open url on webView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getBundleFromIntent();
        //dummyList();
        initializeRecyclerView();
        setAdapter();

    }

    private void getBundleFromIntent(){

        Object obj = getIntent().getExtras().get("news");
        if(obj != null){
            news = (List<Result>) obj;
        }
    }

//    public void dummyList(){
//        Result result = new Result();
//        result.setTitle("A distinguished title for a news story.");
//        result.setAbstract("Very long description of what is supposed to be in here, Very long description of what is supposed to be in here, Very long description of what is supposed to be in here.");
//        result.setThumbnailStandard("http://static01.nyt.com/images/2015/06/29/travel/29WTGNow-Asheville-1/29WTGNow-Asheville-1-thumbStandard.jpg");
//
//        news.add(result);
//        news.add(result);
//        news.add(result);
//        news.add(result);
//        news.add(result);
//        news.add(result);
//        news.add(result);
//        news.add(result);
//        news.add(result);
//        news.add(result);
//
//    }

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
