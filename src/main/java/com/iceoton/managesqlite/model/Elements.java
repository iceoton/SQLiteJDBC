package com.iceoton.managesqlite.model;

/**
 * Created by jaturon on 3/12/2016 AD.
 */
public class Elements {
    int distance;
    int duration;

    public Elements() {
        this.distance = 0;
        this.duration = 0;
    }

    public Elements(int distance, int duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

