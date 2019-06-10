package test.tihonova.sem3.domain;

import test.tihonova.sem3.other.Fuel;

import java.util.Objects;

public class Bus extends Car {
    private int numberOfSeats;
    private int numberOfDoors;

    public Bus() {
        super();
        this.numberOfSeats = 0;
        this.numberOfDoors = 0;
    }

    public Bus(String name, String color, Fuel fuel, int numberOfSeats, int numberOfDoors) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bus bus = (Bus) o;
        return numberOfSeats == bus.numberOfSeats &&
                numberOfDoors == bus.numberOfDoors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfSeats, numberOfDoors);
    }

    @Override
    public String toString() {
        return "name: "+this.getName()+" color: "+this.getColor() +" fuel: "+ this.getFuel()+
                " number of seats:"+this.numberOfSeats+" number of doors: "+this.numberOfDoors+"\n";

    }

    @Override
    public int compareTo(Car o) {
        return super.compareTo(o);
    }


}
