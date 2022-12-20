package com.example.myapplication;

import java.util.Date;

public class CardDataModel {

    private String id;
    private String fio;
    private Integer age;
    private Double height;
    private Double weight;
    private Date welcome, bye;
    //private String photo;

    public CardDataModel(String id, String fio, Integer age, Double height, Double weight,
                         Date welcome, Date bye) {//String photo) {
        this.id = id;
        this.fio = fio;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.welcome = welcome;
        this.bye = bye;
        //this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public Date getWelcome() {
        return welcome;
    }

    public Date getBye() {
        return bye;
    }

    public void setId(String id) {
        this.id = id;
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

//    public String getPhoto() {
//        return photo;
//    }

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

//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }
}
