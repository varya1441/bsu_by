package by.tihonova.javatr.domain.toys;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;

public class Car extends Toy  {
    private String  carModel;

    public Car(String name, double cost, ChildrenGroup childrenGroup, String carModel) {
        super(name,cost, childrenGroup);
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return "Car "+super.toString()+", carModel='" +carModel + '\''+"\n";
    }

}
