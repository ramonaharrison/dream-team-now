package com.ramonaharrison.dev.dreamteamnow;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ramonaharrison.dev.dreamteamnow.NYTAPI.Doc;
import com.ramonaharrison.dev.dreamteamnow.NYTAPI.NYTSearchAPI;
import com.ramonaharrison.dev.dreamteamnow.NYTAPI.NYTSearchModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by c4q-anthonyf on 7/24/15.
 */
public class NewsSearchFragment extends Fragment implements View.OnClickListener {

    private static int beginDay;
    private static int beginMonth;
    private static int beginYear;

    private static int endDay;
    private static int endMonth;
    private static int endYear;

    private EditText searchQuery;
    private EditText beginDate;
    private EditText endDate;

    private Button searchButton;

    private DatePickerDialog beginDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    final String NEWSSEARCH_URL = "http://api.nytimes.com/svc/search/v2";
    private List<Doc> queriedResponse;

    ProgressBar progressSearch;

    View rootView;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news_search, container, false);
        context = container.getContext().getApplicationContext();

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

        findViewsById();
        setupDayfields();
        setDateTimeField();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String queryString = searchQuery.getText().toString();
                String fullBeginDate = beginYear + "" +beginMonth +"" + beginDay;
                String fullEndDate =endYear + "" +endMonth +"" + endDay;

                if(queryString.length() > 0){
                    getNewsData(queryString,fullBeginDate,fullEndDate);
                }
                else{
                    Toast.makeText(context, "Don't leave any fields blank!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private void findViewsById() {
        searchQuery = (EditText) rootView.findViewById(R.id.searchQuery);
        searchQuery.requestFocus();

        beginDate = (EditText) rootView.findViewById(R.id.beginDate);
        beginDate.setInputType(InputType.TYPE_NULL);

        endDate = (EditText) rootView.findViewById(R.id.endDate);
        endDate.setInputType(InputType.TYPE_NULL);

        searchButton = (Button) rootView.findViewById(R.id.newsSearchButton);
        progressSearch = (ProgressBar) rootView.findViewById(R.id.progressBarNews);


    }

    private void setupDayfields(){
        beginDay = 1;
        beginMonth = 1;
        beginYear = 1900;

        endDay = 30;
        endMonth = 12;
        endYear = 2015;
    }

    private void setDateTimeField() {
        beginDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        beginDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                beginDate.setText(dateFormatter.format(newDate.getTime()));
                beginDay = dayOfMonth;
                beginMonth = monthOfYear + 1;
                beginYear = year;
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        endDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endDate.setText(dateFormatter.format(newDate.getTime()));
                endDay = dayOfMonth;
                endMonth = monthOfYear + 1;
                endYear = year;
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if(view == beginDate) {
            beginDatePickerDialog.show();
        } else if(view == endDate) {
            endDatePickerDialog.show();
        }
    }


    public void getNewsData(String query, String fBeginDate, String fEndDate){

        progressSearch.setVisibility(View.VISIBLE);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(NEWSSEARCH_URL).build();

        NYTSearchAPI nyt = restAdapter.create(NYTSearchAPI.class);

        nyt.getFeed( new Callback<NYTSearchModel>() {
            @Override
            public void success(NYTSearchModel model, Response response) {

                queriedResponse = model.getResponse().getDocs();
                Log.d("retroNewsSearchSuccess", "URL: " + response.getUrl());
                startNewsSearch();
                progressSearch.setVisibility(View.GONE);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retroNewsSearchFail", "URL: " + error.getUrl() + "||| Error: " + error.toString());
                progressSearch.setVisibility(View.GONE);
                Toast.makeText(context,"Error, check fields.",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void startNewsSearch(){
        NewsFragment newsFragment = new NewsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("query", (Serializable) queriedResponse);
        newsFragment.setArguments(bundle);
        ((MainActivity) getActivity()).replaceFragment(newsFragment);
        getActivity().overridePendingTransition(R.anim.slide_up_from_bottom, R.anim.slide_down_from_top);
    }


}
