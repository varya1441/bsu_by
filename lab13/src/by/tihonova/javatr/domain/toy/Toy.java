package by.tihonova.javatr.domain.toy;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;

public class Toy implements Comparable<Toy> {

    private String name;
    private double cost;
    private ChildrenGroup childrenGroup;

    public Toy() {
        name="null";
        cost=0.0;
        childrenGroup=ChildrenGroup.NEWBORN;
    }

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
    public String toXML() {
        StringBuilder xml = new StringBuilder();
        xml.append("\t<toy>\n");
        xml.append("\t\t<name>").append(name).append("</name>\n");
        xml.append("\t\t<cost>").append(cost).append("</cost>\n");
        xml.append("\t\t<group>").append(childrenGroup).append("</group>\n");
        xml.append("\t</toy>");
        return xml.toString();
    }
    public String toString() {
        return  "name='" + name + '\'' +
                ", group='" + childrenGroup + '\'' +
                ", cost=" + cost+'\'';
    }
}
