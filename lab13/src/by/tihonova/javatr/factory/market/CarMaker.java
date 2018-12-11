package by.tihonova.javatr.factory.market;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;
import by.tihonova.javatr.domain.toys.Car;
import by.tihonova.javatr.factory.ToyFactory;

public class CarMaker implements ToyFactory {

    @Override
    public Toy createToy(String name, Double cost, ChildrenGroup childrenGroup, String other) {
        return new Car(name,cost, childrenGroup, other);
    }
}
