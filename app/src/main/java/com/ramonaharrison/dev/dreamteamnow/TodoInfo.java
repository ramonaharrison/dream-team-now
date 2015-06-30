package com.ramonaharrison.dev.dreamteamnow;

/**
 * Created by Ramona Harrison
 * on 6/21/15.
 */
public class TodoInfo extends CardInfo {
    private long id;
    private String name;
    private String description;
    private String Location;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int reminder;
    private int minutesBefore;

    public TodoInfo(long id, String name, String description, String location, int day, int month, int year, int hour, int minute, int reminder, int minutesBefore) {
        setType("todo");
        this.id = id;
        this.name = name;
        this.description = description;
        Location = location;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.reminder = reminder;
        this.minutesBefore = minutesBefore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getLocation() {
        return Location;
    }

    public String getLocationString() {
        if (getLocation().length() > 0) {
            return "At " + Location + ".";
        } else {
            return "";
        }
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public int getMinutesBefore() {
        return minutesBefore;
    }

    public void setMinutesBefore(int minutesBefore) {
        this.minutesBefore = minutesBefore;
    }

    public String getMinutesBeforeString() {
        int minsBefore = getMinutesBefore();
        if (minsBefore > 0) {
            return (minsBefore + " min before.");
        } else {
            return "No reminder.";
        }
    }

    public String getTimeString() {
        return  month+2 + "/" + day + "/" + year + " @ " + hour + ":" + minute;
    }
}