package test.tihonova.sem3.domain;

import test.tihonova.sem3.other.Fuel;

import java.awt.*;
import java.util.Objects;

public abstract class Car {
    private String name;
    private Color color;
    private Fuel fuel;

    public Car(String name, Color color, Fuel fuel) {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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
}
