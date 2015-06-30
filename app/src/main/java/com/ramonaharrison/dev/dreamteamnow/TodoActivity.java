package com.ramonaharrison.dev.dreamteamnow;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ramonaharrison.dev.dreamteamnow.db.SQLController;

import java.util.Calendar;


public class TodoActivity extends ActionBarActivity {

    private SQLController dbController;

    private EditText mName;
    private EditText mEtDate;
    private EditText mEtTime;
    private EditText mLocation;
    private CheckBox mReminder;
    private EditText mMinutesBefore;

    private String name;
    private String location;
    private static int day;
    private static int month;
    private static int year;
    private static int hour;
    private static int minute;
    private boolean reminder;
    private int minutesBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        initializeView();
        initializeData();
        setupListeners();
    }

    public void createTodo(View v) {
        getDataFromViews();
        if (isValidInput()) {
            saveTodo();
        } else {
            Toast.makeText(this, "Missing info!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeData() {
        day = -1;
        month = -1;
        year = -1;
        hour = -1;
        minute = -1;
    }

    private void getDataFromViews() {
        name = mName.getText().toString();
        location = mLocation.getText().toString();
        reminder = mReminder.isChecked();
        String mins = mMinutesBefore.getText().toString();
        if (mins.length() > 0) {
            minutesBefore = Integer.valueOf(mins);
        }
    }

    private boolean isValidInput(){
        return name.length() > 0;
    }

    private void saveTodo() {
        dbController = new SQLController(this);
        dbController.open();
        dbController.insert(name, "", location, day, month, year, hour, minute, reminder, minutesBefore);
        dbController.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void initializeView() {
        mName = (EditText) findViewById(R.id.editText_name);
        mEtTime = (EditText) findViewById(R.id.editText_time);
        mEtDate = (EditText) findViewById(R.id.editText_day);
        mLocation = (EditText) findViewById(R.id.editText_where);
        mReminder = (CheckBox) findViewById(R.id.checkBox_reminder);
        mMinutesBefore = (EditText) findViewById(R.id.editText_minutesBefore);

        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(mEtTime.getWindowToken(), 0);
        im.hideSoftInputFromWindow(mEtDate.getWindowToken(), 0);


    }

    public void setEtDate() {
        if (month != -1) {
            mEtDate.setText(month + "/" + day + "/" + year);
        }
    }

    public void setEtTime() {
        if (hour != -1) {
            mEtTime.setText(hour + ":" + minute);
        }
    }

    private void setupListeners() {
        mEtTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "timePicker");
                }else {
                    setEtTime();
                }
            }
        });

        mEtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "datePicker");
                }else {
                    setEtDate();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    true);
        }

        public void onTimeSet(TimePicker view, int h, int m) {
            // Do something with the time chosen by the user
            hour = h;
            minute = m;
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int y, int m, int d) {
            // Do something with the date chosen by the user
            year = y;
            month = m-1;
            day = d;
        }
    }
}
