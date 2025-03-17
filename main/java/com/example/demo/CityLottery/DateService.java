package com.example.demo.CityLottery;

import java.util.Calendar;

public class DateService {

    public boolean IsWeekend(Calendar calendar){
        var currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        return (currentDay == Calendar.SATURDAY || currentDay == Calendar.SUNDAY);
    }
}
