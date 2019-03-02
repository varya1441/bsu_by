package com.tihonova.domain;

public abstract class Animal implements Comparable<Animal>{
    private String name;
    private String place;


    public Animal() {
        this.name="none";
        this.place="none";
    }
    public Animal(String name, String place) {
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
    public int compareTo(Animal o) {
        int cmpPlace=this.getPlace().compareTo(o.getPlace());
        int cmpName=this.getName().compareTo(o.getName());
        if(cmpPlace!=0){
            return  cmpPlace;
        }
            return cmpName;

    }
public abstract int massOfPray();
    @Override
    public String toString() {
        return    "name='" + name + '\'' +
                ", place='" + place + '\'';
    }
}
