package com.example.shareonfoot.util;

public class Location {
    private String name;
    private Float lng;
    private Float lat;

    public Location() {
        super();
    }
    public Location(String name,Float lng, Float lat) {
        super();
        this.name = name;
        this.lng = lng;
        this.lat = lat;

    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Float getlng() {
        return lng;
    }

    public void setlng(Float lng) {
        this.lng = lng;
    }

    public Float getlat() {
        return lat;
    }
    public void setlat(Float lat) {
        this.lat = lat;
    }
}
