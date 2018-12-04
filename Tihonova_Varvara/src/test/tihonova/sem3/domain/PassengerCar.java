package test.tihonova.sem3.domain;

import test.tihonova.sem3.other.Fuel;
import test.tihonova.sem3.other.Material;

import java.awt.*;

public class PassengerCar extends Car {
    private Material material;

    public PassengerCar(String name, Color color, Fuel fuel, Material material) {
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
    public String toString() {
        return "name: "+this.getName()+" color: "+this.getColor() +" fuel: "+ this.getFuel()+"material:"+this.material+"\n";
    }

}
