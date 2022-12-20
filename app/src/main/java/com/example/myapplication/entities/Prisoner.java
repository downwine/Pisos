package com.example.myapplication.entities;

import com.example.myapplication.entities.Case;

import java.util.Date;

public class Prisoner {
    public String id, name;
    public Integer age;
    public Float height, weight;
    public Date welcome, bye;
    public Case id_criminal_case;

    public Prisoner(String id, String name, Integer age, Float height, Float weight, Date welcome, Date bye, Case id_criminal_case) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.welcome = welcome;
        this.bye = bye;
        this.id_criminal_case = id_criminal_case;
    }
}
