package com.example.myapplication.entities;

import java.util.Date;

public class Prisoner {
    public String id, name;
    public Integer age;
    public Double height;
    public Double weight;
    public Date welcome, bye;
    public Case id_criminal_case;

    public Prisoner() {

    }

    public Prisoner(String name, Integer age, Double height, Double weight, String id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.welcome = welcome;
        this.bye = bye;
//        this.id_criminal_case = id_criminal_case;
    }
}
