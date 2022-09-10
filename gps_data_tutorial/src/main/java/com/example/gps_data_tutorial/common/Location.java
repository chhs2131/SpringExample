package com.example.gps_data_tutorial.common;

public class Location {
    private double lat;
    private double lon;

    public Location(double lat, double lon) {
        verifyLat(lat);
        verifyLon(lon);
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public void setLat(double lat) {
        verifyLat(lat);
        this.lat = lat;
    }

    public void setLon(double lon) {
        verifyLon(lon);
        this.lon = lon;
    }

    private void verifyLat(double lat) {
        if(lat < -90 || 90 < lat) {
            throw new IllegalArgumentException("lat 값이 범위를 벗어났습니다.");
        }
    }

    private void verifyLon(double lon) {
        if(lon < -180 || 180 < lon) {
            throw new IllegalArgumentException("lon 값이 범위를 벗어났습니다.");
        }
    }
}
