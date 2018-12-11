package by.tihonova.javatr.creation;

import by.tihonova.javatr.factory.*;
import by.tihonova.javatr.factory.market.BallMaker;
import by.tihonova.javatr.factory.market.CarMaker;
import by.tihonova.javatr.factory.market.DollMaker;
import by.tihonova.javatr.factory.market.RobotMaker;

public class ToyCreation {

    public static ToyFactory getToyMarket(String toyType){
        if ("Ball".equals(toyType)) {
            return new BallMaker();
        } else if ("Car".equals(toyType)) {
            return new CarMaker();
        } else if ("Doll".equals(toyType)) {
            return new DollMaker();
        } else if ("Robot".equals(toyType)) {
            return new RobotMaker();
        }
        return null;
    }
}
