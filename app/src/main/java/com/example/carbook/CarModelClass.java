package com.example.carbook;

public class CarModelClass {
    private String poster;
    private String carModelName;
    private String carModelDesc;


    public CarModelClass(String poster, String carModelName, String carModelDesc) {
        this.poster = poster;
        this.carModelName = carModelName;
        this.carModelDesc = carModelDesc;
    }
    public String getPoster() {
        return poster;
    }
    public String getCarModelName() {
        return carModelName;
    }
    public String getCarModelDesc() {
        return carModelDesc;
    }

}
