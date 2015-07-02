package com.ramonaharrison.dev.dreamteamnow;

import java.util.Calendar;

/**
 * Created by c4q-anthonyf on 7/1/15.
 */
public class HeaderInfo extends CardInfo{

    Calendar calendar;

    public HeaderInfo(){
        setPriority(-1);
        setType("header");
        calendar = Calendar.getInstance();
    }

    public String timeOfDay(){

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour >= 5 && hour <= 15){
            return "day";
        }else if(hour > 15 && hour <= 20){
            return "midday";
        }else
            return "night";

    }

}
