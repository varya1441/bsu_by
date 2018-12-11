package by.tihonova.javatr.domain.toys;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;

public class Doll extends Toy {
    private String dressDesigner;

    public Doll(String name, double cost, ChildrenGroup childrenGroup, String dressDesigner) {
        super(name,cost, childrenGroup);
        this.dressDesigner = dressDesigner;
    }

    public String getDressDesigner() {
        return dressDesigner;
    }

    public void setDressDesigner(String dressDesigner) {
        this.dressDesigner = dressDesigner;
    }

    @Override
    public String toString() {
        return  "Doll "+super.toString()+", dressDesigner='" + dressDesigner + '\''+"\n";
    }


}
