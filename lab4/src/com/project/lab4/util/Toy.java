package com.project.lab4.util;

public class Toy implements Comparable<Toy>{

    private String color;
    private double weight;
    private String name;

    public Toy(){
        color="white";
        weight =0;
        name="noname";
    }
    public Toy(String color, double weight, String name){
        this.color=color;
        this.weight = weight;
        this.name=name;
    }
    @Override
    public int compareTo(Toy toy){
        if (this.weight < toy.weight) {
            return -1;
        }
        if (this.weight == toy.weight) {
            return 0;
        }
        return 1;
    }
    public String toString(){
        return name;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
