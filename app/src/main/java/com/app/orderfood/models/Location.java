package com.app.orderfood.models;

public class Location {
    private int id;
    public String loc;

    public Location() {
    }

    public Location(int id, String loc) {
        this.id = id;
        this.loc = loc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
