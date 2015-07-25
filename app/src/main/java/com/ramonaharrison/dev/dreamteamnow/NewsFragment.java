package com.ramonaharrison.dev.dreamteamnow;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Doc;
import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-anthonyf on 7/24/15.
 */
public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List news = new ArrayList<>(0);

    private View rootView;
    private Context context;
    // add menu item to close activity/go back


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news,container,false);

        context = container.getContext();

        getBundleFromIntent();
        initializeRecyclerView();
        setAdapter();

        return rootView;
    }

    private void getBundleFromIntent(){

        if(getArguments().get("news") != null) {
            Object obj = getArguments().get("news");
            if (obj != null) {
                news = (List<Result>) obj;
            }
        }
        else if(getArguments().get("query") != null){
            Object obj = getArguments().get("query");
            if (obj != null) {
                news = (List<Doc>) obj;
            }
        }
    }

    private void initializeRecyclerView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.newsList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter() {
        newsAdapter = new NewsAdapter(news, context);
        recyclerView.setAdapter(newsAdapter);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_news, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_search){
            ((MainActivity) getActivity()).replaceFragment(new NewsSearchFragment());
            getActivity().overridePendingTransition(R.anim.slide_up_from_bottom, R.anim.slide_down_from_top);
        }
        if (id == R.id.action_close) {
            ((MainActivity) getActivity()).replaceFragment(new PlaceHolderFragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
