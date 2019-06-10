package test.tihonova.sem3.reader;

import test.tihonova.sem3.container.MyContainer;
import test.tihonova.sem3.domain.PassengerCar;
import test.tihonova.sem3.domain.Bus;
import test.tihonova.sem3.other.Fuel;
import test.tihonova.sem3.other.Material;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFromFile {
    static Scanner scanner = null;

    public static MyContainer<Bus> readB(FileReader fileReader) throws IllegalArgumentException {
        MyContainer<Bus> myContainer = new MyContainer<>();
        scanner = new Scanner(new BufferedReader(fileReader));

        while (scanner.hasNext()) {
            myContainer.add(new Bus(scanner.next(),
                    scanner.next(),
                    Fuel.valueOf(scanner.next().toUpperCase()),
                    scanner.nextInt(),
                    scanner.nextInt()));
        }

        scanner.close();
        return myContainer;
    }

    public static MyContainer<PassengerCar> readPC(FileReader fileReader) throws IllegalArgumentException {
        MyContainer<PassengerCar> myContainer = new MyContainer<>();
        scanner = new Scanner(new BufferedReader(fileReader));
        while (scanner.hasNext()) {

            myContainer.add(new PassengerCar(scanner.next(),
                    scanner.next(),
                    Fuel.valueOf(scanner.next()),
                    Material.valueOf(scanner.next())));

        }

        return myContainer;
    }
}
