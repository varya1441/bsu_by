package test.tihonova.sem3.domain;

import test.tihonova.sem3.other.Fuel;

import java.awt.*;
import java.util.Comparator;
import java.util.Objects;

public abstract class Car implements Comparable<Car>{
    private String name;
    private String color;
    private Fuel fuel;

    public Car() {
        name="noname";
        color="white";
        fuel=Fuel.PETROL;
    }

    public Car(String name, String color, Fuel fuel) {
        this.name = name;
        this.color = color;
        this.fuel = fuel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) &&
                Objects.equals(color, car.color) &&
                fuel == car.fuel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, fuel);
    }

    @Override
    public abstract String toString();

    @Override
    public int compareTo(Car o) {
        int cmp=name.compareTo(o.name);

        if(cmp==0){
            return  o.getFuel().name().compareTo(fuel.name());
        }
            return cmp;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
