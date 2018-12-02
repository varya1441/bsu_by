package test.tihonova.sem3.domain;

import test.tihonova.sem3.other.Fuel;

import java.awt.*;

public class Truck extends Car {
    private int numberOfSeats;
    private int numberOfDoors;

    public Truck(String name, Color color, Fuel fuel, int numberOfSeats, int numberOfDoors) {
        super(name, color, fuel);
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String toString() {
        return "name: "+this.getName()+" color: "+this.getColor() +" fuel: "+ this.getFuel()+
                "number of seats:"+this.numberOfSeats+" number of doors: "+this.numberOfDoors+"\n";

    }
}
