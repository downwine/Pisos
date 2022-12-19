package com.example.myapplication;

import android.text.format.Time;

public class Timetable {
    public String id;
    public Activity id_activity;
    public Time start_time, end_time;

    public Timetable(String id, Activity id_activity, Time start_time, Time end_time) {
        this.id = id;
        this.id_activity = id_activity;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
