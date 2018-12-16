package by.tihonova.javatr.reader;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ReadFromFile {

    static Scanner scanner = null;

    public static Set<Toy> read(FileReader fileReader) throws IllegalArgumentException, InputMismatchException {
        Set<Toy> myContainer = new TreeSet<>();
        scanner = new Scanner(new BufferedReader(fileReader));
        while (scanner.hasNext()) {

            myContainer.add(new Toy(scanner.next(), scanner.nextDouble(),
                    ChildrenGroup.valueOf(scanner.next().toUpperCase())));
        }

        return myContainer;
    }
}

