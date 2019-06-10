package test.tihonova.sem3.domain;

import test.tihonova.sem3.other.Fuel;
import test.tihonova.sem3.other.Material;

import java.awt.*;
import java.util.Objects;

public class PassengerCar extends Car {
    private Material material;

    public PassengerCar() {
        super();
        material=Material.COTTON;
    }

    public PassengerCar(String name, String color, Fuel fuel, Material material) {
        super(name, color, fuel);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassengerCar that = (PassengerCar) o;
        return material == that.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), material);
    }

    @Override
    public String toString() {
        return "name: "+this.getName()+" color: "+this.getColor() +" fuel: "+ this.getFuel()+" material:"+this.material+"\n";
    }

}
