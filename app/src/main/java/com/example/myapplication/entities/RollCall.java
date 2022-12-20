package com.example.myapplication.entities;

import com.example.myapplication.entities.Activity;
import com.example.myapplication.entities.Prisoner;

import java.util.Date;

public class RollCall {
    public String id;
    public Date date;
    public Prisoner id_prisoner;
    public Activity id_activity;

    public RollCall(String id, Date date, Prisoner id_prisoner, Activity id_activity) {
        this.id = id;
        this.date = date;
        this.id_prisoner = id_prisoner;
        this.id_activity = id_activity;
    }
}
