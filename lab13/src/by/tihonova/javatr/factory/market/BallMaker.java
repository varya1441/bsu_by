package by.tihonova.javatr.factory.market;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;
import by.tihonova.javatr.domain.toys.Ball;
import by.tihonova.javatr.factory.ToyFactory;

public class BallMaker implements ToyFactory {

    @Override
    public Toy createToy(String name, Double cost, ChildrenGroup childrenGroup, String other) {
        return new Ball(name,cost, childrenGroup, other);
    }
}
