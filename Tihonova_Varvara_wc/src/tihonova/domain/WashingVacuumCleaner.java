package tihonova.domain;



public class WashingVacuumCleaner extends VacuumCleaner {
   private int waterContainerVolume;


    public WashingVacuumCleaner() {
        super();
        waterContainerVolume=0;
    }

    public WashingVacuumCleaner(String firmName, int power, String color, String image, int waterContainerVolume) {
        super(firmName, power, color, image);
        this.waterContainerVolume = waterContainerVolume;
    }

    public int getWaterContainerVolume() {
        return waterContainerVolume;
    }

    public void setWaterContainerVolume(int waterContainerVolume) {
        this.waterContainerVolume = waterContainerVolume;
    }

    @Override
    public String toString() {
        return "WashingVacuumCleaner{" +
                super.toString()+
                "waterContainerVolume=" + waterContainerVolume +
                '}';
    }
}
