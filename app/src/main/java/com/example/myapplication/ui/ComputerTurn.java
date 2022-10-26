package com.example.myapplication.ui;

public class ComputerTurn {
    private Integer fingerPhoto;
    private Integer totalScore;

    public ComputerTurn(Integer fingerPhoto, Integer totalScore) {
        this.fingerPhoto = fingerPhoto;
        this.totalScore = totalScore;
    }

    public Integer getFingerPhoto() {
        return fingerPhoto;
    }

    public void setFingerPhoto(Integer fingerPhoto) {
        this.fingerPhoto = fingerPhoto;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
