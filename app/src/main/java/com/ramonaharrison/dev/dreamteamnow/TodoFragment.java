package com.ramonaharrison.dev.dreamteamnow;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ramonaharrison.dev.dreamteamnow.db.SQLController;

import java.util.Calendar;

/**
 * Created by c4q-anthonyf on 7/23/15.
 */
public class TodoFragment extends Fragment {

    private SQLController dbController;

    private EditText mName;
    private EditText mEtDate;
    private EditText mEtTime;
    private EditText mLocation;
    private CheckBox mReminder;
    private EditText mMinutesBefore;
    private FloatingActionButton floatingActionButton;

    private String name;
    private String location;
    private static int day;
    private static int month;
    private static int year;
    private static int hour;
    private static int minute;
    private boolean reminder;
    private int minutesBefore;

    private View rootView;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_todo,container, false);
        context = container.getContext().getApplicationContext();

        initializeViews();
        initializeData();
        setupListeners();

        return rootView;
    }

    public void createTodo(View v) {
        getDataFromViews();
        if (isValidInput()) {
            saveTodo();
        } else {
            Toast.makeText(context, "Missing info!", Toast.LENGTH_SHORT).show();
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
        dbController = new SQLController(context);
        dbController.open();
        dbController.insert(name, "", location, day, month, year, hour, minute, reminder, minutesBefore);
        dbController.close();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.refreshMainFragment();

    }

    private void initializeViews() {
        mName = (EditText) rootView.findViewById(R.id.editText_name);
        mEtTime = (EditText) rootView.findViewById(R.id.editText_time);
        mEtDate = (EditText) rootView.findViewById(R.id.editText_day);
        mLocation = (EditText) rootView.findViewById(R.id.editText_where);
        mReminder = (CheckBox) rootView.findViewById(R.id.checkBox_reminder);
        mMinutesBefore = (EditText) rootView.findViewById(R.id.editText_minutesBefore);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.todo_action_button);

        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
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
                    newFragment.show(((MainActivity)getActivity()).getSupportFragmentManager(), "timePicker");
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
                    newFragment.show(((MainActivity)getActivity()).getSupportFragmentManager(), "datePicker");
                }else {
                    setEtDate();
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTodo(view);
            }
        });
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
