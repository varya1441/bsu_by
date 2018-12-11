package by.tihonova.javatr.domain.toy;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;

public abstract class Toy implements Comparable<Toy> {

    private String name;
    private double cost;
    private ChildrenGroup childrenGroup;

    public Toy(String name, Double cost, ChildrenGroup childrenGroup) {
        this.name = name;
        this.cost = cost;
        this.childrenGroup = childrenGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ChildrenGroup getChildrenGroup() {
        return childrenGroup;
    }

    public void setChildrenGroup(ChildrenGroup childrenGroup) {
        this.childrenGroup = childrenGroup;
    }

    @Override
    public int compareTo(Toy o) {
        if(this.childrenGroup.name().equals(o.childrenGroup.name())) {
            return Double.compare(this.cost, o.cost);
        }
        return this.childrenGroup.name().compareTo(o.childrenGroup.name());
    }

    public String toString() {
        return  "name='" + name + '\'' +
                ", group='" + childrenGroup + '\'' +
                ", cost=" + cost+'\'';
    }
}