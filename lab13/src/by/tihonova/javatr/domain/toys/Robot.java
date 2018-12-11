package by.tihonova.javatr.domain.toys;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;

public class Robot extends Toy  {

    private String functionality;

    public Robot(String name, double cost, ChildrenGroup childrenGroup, String functionality) {
        super(name,cost, childrenGroup);
        this.functionality = functionality;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    @Override
    public String toString() {
        return "Robot "+super.toString()+", functionality='" + functionality+'\''+"\n" ;
    }
}
