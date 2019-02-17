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

    public double getPower() {
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
//        int cmpCost=Double.compare(o.getColor(),this.color);
//        int cmpCaffeineAmount=Double.compare(this.power,o.getPower());
//        if (cmpCost!=0)
//            return cmpCost;
//        else if (cmpCaffeineAmount!=0)
//            return cmpCaffeineAmount;
//        else
//            return this.firmName.compareTo(o.getFirmName());
        return 0;
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
