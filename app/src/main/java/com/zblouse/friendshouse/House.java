package com.zblouse.friendshouse;

import java.io.Serializable;

/**
 * Basic POJO for a house object
 */
public class House implements Serializable {
    private int id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;

    public House(String name, String description, double latitude, double longitude){
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public House(int id, String name, String description, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }
}
