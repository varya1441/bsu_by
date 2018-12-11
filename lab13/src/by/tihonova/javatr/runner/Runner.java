package by.tihonova.javatr.runner;

import by.tihonova.javatr.application.Application;
//import by.tihonova.javatr.domain.PlayRoom;
import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;
import by.tihonova.javatr.domain.toys.Ball;
import by.tihonova.javatr.reader.ReadFromFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;

public class Runner {
   // final static String FILE_1 = "input.txt";

    public static void main(String[] args) {
        Application app=new Application();
        app.setVisible(true);
//        ChildrenGroup group;
//
//        try {
//            FileReader fileReader = new FileReader(FILE_1);
//            PlayRoom<Toy> toys = ReadFromFile.read(fileReader);
//
//            toys.print();
//
//            toys.add(new Ball("MyBall", 12,ChildrenGroup.PRESCHOOL,"yellow"));
//
//            toys.print();
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } catch (InputMismatchException | IllegalArgumentException e) {
//            System.out.println("Wrong arguments");
//        }
    }
}
