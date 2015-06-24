package com.ramonaharrison.dev.dreamteamnow;

import java.util.Calendar;

/**
 * Created by Ramona Harrison
 * on 6/21/15.
 */
public class TodoInfo extends CardInfo {
    private String name;
    private String description;
    private Calendar when;
    private boolean isReminder;
    private int minutesBefore;

    public TodoInfo(String name, String description, Calendar when, boolean isReminder, int minBeforeEvent) {
        this.setType("todo");
        this.name = name;
        this.description = description;
        this.when = when;
        this.isReminder = isReminder;
        this.minutesBefore = minBeforeEvent;
    }

    public TodoInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getWhen() {
        return when;
    }

    public String getWhenString() { return "fix this!"; }

    public void setWhen(Calendar when) {
        this.when = when;
    }


    public String getWhereString() { return "fix this too!"; }

    public boolean isReminder() {
        return isReminder;
    }

    public void setReminder(boolean isReminder) {
        this.isReminder = isReminder;
    }

    public int getMinutesBefore() {
        return minutesBefore;
    }

    public void setMinutesBefore(int minutesBefore) {
        this.minutesBefore = minutesBefore;
    }
}