package com.example.gps_data_tutorial.common;

public class Distance {
    private double degrees;

    public Distance(double degrees) {
        this.degrees = degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public double getDegrees() {
        return degrees;
    }

    public double getKilometer() {
        return degrees * 60 * 1.1515 * 1.609344;
    }

    public double getMeter() {
        return degrees * 60 * 1.1515 * 1609.344;
    }

    public double getMile() {
        return degrees * 60 * 1.1515;
    }
}
