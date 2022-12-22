package com.example.myapplication.ui.cases;

public class CaseDataModel {

    private String name;
    private String desc;

    public CaseDataModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
