package com.example.myapplication;

public class CardDataModel {

    private String fio;
    private Integer age;
    private Double height;
    private Double weight;
    private String photo;

    public CardDataModel(String fio, Integer age, Double height, Double weight, String photo) {
        this.fio = fio;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.photo = photo;
    }

//

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
