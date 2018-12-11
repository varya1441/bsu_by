package by.tihonova.javatr.domain.toys;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;

public class Ball extends Toy  {
    private String color;

    public Ball(String name, double cost, ChildrenGroup childrenGroup, String color) {
        super(name,cost, childrenGroup);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return  "Ball "+super.toString()+", color='" +color + '\''+"\n";
    }


}
