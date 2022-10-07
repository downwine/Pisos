package com.example.myapplication;

public class CardDataModel {

    private String fio;
    private String age;
    private String height;
    private String weight;
    private Integer photo;

    public CardDataModel(String fio, String age, String height, String weight, Integer photo) {
        this.fio = fio;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.photo = photo;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getPhoto() {
        return photo;
    }

    public void setPhoto(Integer photo) {
        this.photo = photo;
    }
}
