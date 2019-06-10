package tihonova.domain;


public class RobotVacuumCleaner extends VacuumCleaner {
   private boolean screen;

    public RobotVacuumCleaner() {
        super();
        this.screen = false;

    }

    public RobotVacuumCleaner(String firmName, int power, String color, String image, boolean screen) {
        super(firmName, power, color, image);
        this.screen = screen;
    }

    public boolean isScreen() {
        return screen;
    }

    public void setScreen(boolean screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "RobotVacuumCleaner{" +
                super.toString()+
                "screen=" + screen +
                '}';
    }
}
