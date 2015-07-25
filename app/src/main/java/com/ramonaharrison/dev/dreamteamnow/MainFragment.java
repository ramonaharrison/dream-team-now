package com.ramonaharrison.dev.dreamteamnow;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ramonaharrison.dev.dreamteamnow.db.SQLController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by c4q-anthonyf on 7/23/15.
 */
public class MainFragment extends Fragment implements OnMapReadyCallback, PopupMenu.OnMenuItemClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CoordinatorLayout mainContent;
    private List<CardInfo> cards;
    private SQLController dbController;
    private GoogleApiClient mGoogleApiClient;
    private CardAdapter cAdapter;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private Calendar calendar;
    private static View mapItemView;
    private FloatingActionButton floatingActionButton;

    private Context context;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        context = container.getContext();

        buildGoogleApiClient();
        mainContent = (CoordinatorLayout) rootView.findViewById(R.id.main_content);

        initializeRefreshLayout();
        initializeViews();
        initializeCards();
        setAdapter();
        setItemTouchHelper(cAdapter);

        return rootView;
    }

    @Override
    public void onConnected(Bundle bundle) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mCurrentLocation != null) {
            createLocationRequest();
            startLocationUpdates();
            initializeMap();
        } else {

        }
    }

    private void initializeCards() {
        //TODO: add your cards to the deck here
        cards = new ArrayList<CardInfo>();
        initializeTrend();
        initializeTodo();
        initializeWeather();
        sortCardList(cards);

    }

    private void initializeWeather() {
        cards.add(new WeatherInfo(context));
    }

    private void initializeMap() {
        MapInfo mapInfo = new MapInfo("Your Location", mCurrentLocation);
        cards.add(mapInfo);
    }
    private void initializeTrend(){
        TrendInfo trend = new TrendInfo();
        cards.add(trend);

    }

    // uses SwipeRefreshLayout to implement pull to refresh feature
    private void initializeRefreshLayout(){
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        int[] colorScheme = {Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.GREEN };
        swipeRefreshLayout.setColorSchemeColors(colorScheme);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                initializeCards();
                cAdapter = new CardAdapter(cards, context,mapItemView);
                recyclerView.swapAdapter(cAdapter, false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    public static void setMapItemView(View mapIV){
        mapItemView = mapIV;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    private void initializeViews() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTodo();
            }
        });

    }

    private void setAdapter() {
        cAdapter = new CardAdapter(cards, context);
        recyclerView.setAdapter(cAdapter);

    }

    //ItemTouch Helper for swiping/dragging
    public void setItemTouchHelper(CardAdapter adapter){
        ItemTouchHelper.Callback callback = new CustomItemTouchHelper(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    //onClick for more button
    public void moreNews(View v){

    }



    public void createTodo() {
        ((MainActivity)getActivity()).replaceFragment(new TodoFragment());
    }

    private void initializeTodo() {
        dbController = new SQLController(context);
        dbController.open();
        Cursor cursor = dbController.fetch();

        int idIndex = cursor.getColumnIndexOrThrow("_id");
        int nameIndex = cursor.getColumnIndexOrThrow("name");
        int descriptionIndex = cursor.getColumnIndexOrThrow("description");
        int locationIndex = cursor.getColumnIndexOrThrow("location");
        int dayIndex = cursor.getColumnIndexOrThrow("day");
        int monthIndex = cursor.getColumnIndexOrThrow("month");
        int yearIndex = cursor.getColumnIndexOrThrow("year");
        int hourIndex = cursor.getColumnIndexOrThrow("hour");
        int minuteIndex = cursor.getColumnIndexOrThrow("minute");
        int reminderIndex = cursor.getColumnIndexOrThrow("reminder");
        int minBeforeIndex = cursor.getColumnIndexOrThrow("minbefore");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            try {
                long id = cursor.getLong(idIndex);
                String name = cursor.getString(nameIndex);
                String description = cursor.getString(descriptionIndex);
                String location = cursor.getString(locationIndex);
                int day = cursor.getInt(dayIndex);
                int month = cursor.getInt(monthIndex);
                int year = cursor.getInt(yearIndex);
                int hour = cursor.getInt(hourIndex);
                int minute = cursor.getInt(minuteIndex);
                int reminder = cursor.getInt(reminderIndex);
                int minBefore = cursor.getInt(minBeforeIndex);

                TodoInfo newTodo = new TodoInfo(id, name, description, location, day, month, year, hour, minute, reminder, minBefore);
                cards.add(newTodo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        cursor.close();
        dbController.close();
    }

    //uses insertion sort algorithm to sort cards based on priority
    public void sortCardList(List<CardInfo> cardList){
        for(int i = 1; i < cardList.size(); i++){
            int currentPriority = cardList.get(i).getPriority();
            int previousPriority = cardList.get(i-1).getPriority();
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

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        updateMap();
    }

    private void updateMap() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
