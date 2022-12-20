package com.example.myapplication.entities;

import com.example.myapplication.entities.Timetable;

public class Week {
    public String id, day;
    public Timetable id_timetable;

    public Week(String id, String day, Timetable id_timetable) {
        this.id = id;
        this.day = day;
        this.id_timetable = id_timetable;
    }
}
