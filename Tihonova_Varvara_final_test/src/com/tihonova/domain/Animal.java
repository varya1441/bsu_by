package com.tihonova.domain;

import java.util.Objects;

public class Animals implements Comparable<Animals>{
    private String name;
    private String place;

    public Animals() {
    }
    public Animals(String name, String place) {
        this.name = name;
        this.place = place;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public int compareTo(Animals o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Animals{" +
                "name='" + name + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
