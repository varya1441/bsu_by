package test.tihonova.sem3.runner;

import test.tihonova.sem3.container.MyContainer;
import test.tihonova.sem3.domain.PassengerCar;
import test.tihonova.sem3.domain.Bus;
import test.tihonova.sem3.exception.CollectionIsEmpty;
import test.tihonova.sem3.other.Fuel;
import test.tihonova.sem3.other.Material;
import test.tihonova.sem3.reader.ReadFromFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Runner {
    final static String FILE_1 = "input1.txt";
    final static String FILE_2 = "input2.txt";

    public static void main(String[] args) {
        MyContainer<Bus> buses = new MyContainer<>();
        MyContainer<PassengerCar> passengerCars = new MyContainer<>();
        PassengerCar passengerCar = new PassengerCar("Cherry", "Blue", Fuel.PETROL, Material.LEATHER);
        Bus bus = new Bus("Cherry", "Blue", Fuel.PETROL, 3, 3);
        Bus bus1 = new Bus("Fordes", "Blue", Fuel.PETROL, 3, 3);
        Scanner scanner = null;
        try {
            FileReader fileReaderPC = new FileReader(FILE_1);
            FileReader fileReaderB = new FileReader(FILE_2);

            passengerCars = ReadFromFile.readPC(fileReaderPC);

            System.out.println("All passangercars:\n");
            passengerCars.print();
            System.out.println("find max\n" + passengerCars.findMax());
            System.out.println("find count of " + passengerCar.toString() +"is: "+ passengerCars.find(passengerCar));
            try {
                System.out.println("search " + passengerCars.search(passengerCar));
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("No such element\n");
            }

            System.out.println("all elements after searching:\n");
            passengerCars.print();

            buses = ReadFromFile.readB(fileReaderB);

            System.out.println("All buses:\n");
            buses.print();
            System.out.println("find max " + buses.findMax());
            System.out.println("find count of " + bus.toString()+ "is: "+ buses.find(bus));

            try {
                System.out.println("search bus " + buses.search(bus1));
            } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No such element\n");
        }
            System.out.println("all elements after searching:\n");
            buses.print();

        }catch ( CollectionIsEmpty e) {
            System.out.println("Exception" + e.getMessage());
        }  catch (IllegalArgumentException ex) {
            System.out.println("Illegal argument");
        } catch (IOException e) {
            System.out.println("File not found");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}
