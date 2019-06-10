package tihonova.domain;

import java.util.Objects;

public class VacuumCleaner implements Comparable<VacuumCleaner> {
    private String firmName;
    private int power;
    private String color;
    private String image;

    public VacuumCleaner() {
        this.firmName = "none";
        this.power = 0;
        this.color = "none";
        this.image = "none";
    }

    public VacuumCleaner(String firmName, int power, String color, String image) {
        this.firmName = firmName;
        this.power = power;
        this.color = color;
        this.image = image;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int compareTo(VacuumCleaner o) {
        int cmpFirmName=o.getFirmName().compareTo(this.firmName);
        int cmpPower=Integer.compare(o.getPower(),this.power);
        if(cmpFirmName!=0){
        return cmpFirmName;
        }else if(cmpPower!=0){
            return cmpPower;
        }
        return this.getColor().compareTo(o.getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacuumCleaner that = (VacuumCleaner) o;
        return Double.compare(that.power, power) == 0 &&
                Objects.equals(firmName, that.firmName) &&
                Objects.equals(color, that.color) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firmName, power, color, image);
    }

    @Override
    public String toString() {
        return  "firmName='" + firmName + '\'' +
                ", power=" + power +
                ", color='" + color + '\'' +
                ", image='" + image + '\'';
    }
}
