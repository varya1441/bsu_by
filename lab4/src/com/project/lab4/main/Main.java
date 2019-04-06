package com.project.lab4.main;
import com.project.lab4.scanner.MyScanner;
import com.project.lab4.util.*;

import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
       Tree<Integer> t=new Tree<>();
//        t.add(0);
//        t.add(100);
//        t.add(-100);

        t.add(-21);
        t.add(2);
        t.add(3);
        t.add(4);
        t.add(0);
        t.add(-1);
        t.add(2);
        t.add(1);


        t.goRootLeftRight();
    //    t.goLeftRightRoot();

       System.out.println("Element to delete: ");

       int elemToDelete=MyScanner.nextInt();

        t.delete(elemToDelete);
        t.goLeftRightRoot();
        t.goLeftRootRight();
        t.goRootLeftRight();

        System.out.println("Enter element to find");

        int elemToFind=MyScanner.nextInt();

        if(t.search(elemToFind)){
            System.out.println("Element is find");
        }else{
            System.out.println("No such element");
        }

        Tree<Toy> toyTree=new Tree<>();

        String  colorOfToy;String nameOfToy; double weightOfToy;

        System.out.println("Enter how much toys you want: ");

        int number=MyScanner.nextInt();

        for (int i=0;i<number;i++) {
            System.out.println("Enter next toy ");
            System.out.println("Enter name: ");
            nameOfToy = MyScanner.nextString();
            System.out.println("Enter color: ");
            colorOfToy = MyScanner.nextString();
            System.out.println("Enter weight: ");
            try {
                weightOfToy = MyScanner.nextDouble();

                toyTree.add(new Toy(colorOfToy, weightOfToy, nameOfToy));

            }catch(InputMismatchException e){

                System.out.println("is not double, will not be compared");
            }

        }
        System.out.println("Sorted toys by weight:");

        toyTree.goLeftRootRight();
    }
}
