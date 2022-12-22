package com.example.myapplication.ui.cards;

import java.util.Date;

public class CardDataModel {

    private String name_criminal_case;
    private String fio;
    private Integer age;
    private Double height;
    private Double weight;
    private Date welcome, bye;
    private String photo;

    //ANNYS ANNUS АННУС
    public CardDataModel(String name_criminal_case, String fio, Integer age, Double height, Double weight,
                         Date welcome, Date bye) {//String photo) {
        this.name_criminal_case = name_criminal_case;
        this.fio = fio;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.welcome = welcome;
        this.bye = bye;
        //this.photo = photo;
    }

    public Date getWelcome() {
        return welcome;
    }

    public String getName_criminal_case() {
        return name_criminal_case;
    }

    public void setName_criminal_case(String name_criminal_case) {
        this.name_criminal_case = name_criminal_case;
    }

    public Date getBye() {
        return bye;
    }

    public void setWelcome(Date welcome) {
        this.welcome = welcome;
    }

    public void setBye(Date bye) {
        this.bye = bye;
    }

    public String getFio() {
        return fio;
    }

    public Integer getAge() {
        return age;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public String getPhoto() {
        return photo;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
