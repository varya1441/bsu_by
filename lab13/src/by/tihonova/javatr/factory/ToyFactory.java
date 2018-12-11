package by.tihonova.javatr.factory;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;


public interface ToyFactory {

    Toy createToy(String name, Double cost, ChildrenGroup childrenGroup, String other);
}
