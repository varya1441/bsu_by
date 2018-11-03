package com.project.lab4.scanner;


import java.util.Scanner;

public class MyScanner {
    private static Scanner scanner=new Scanner(System.in);
    public static String nextString(){
        return scanner.next();
    }
    public static Double nextDouble(){
        return scanner.nextDouble();
    }
    public static Integer nextInt(){
        return scanner.nextInt();
    }
    public static boolean hasNext(){
        return scanner.next()!=".";
    }

}
