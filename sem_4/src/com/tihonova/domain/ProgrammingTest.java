package com.tihonova.domain;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ProgrammingTest {
    public static void main(String[] args) throws IOException {
        Set<Student> students=new HashSet<>();
        Academic math=new Academic("math academic");
        Academic science=new Academic("science");

        students.add(new Undergraduate("ved","vvv","v@v.com",math));
        students.add(new Undergraduate("bed","bbb","b@b.com",science));

        students.add(new Postgraduate("ped","pp","p@p.com",math));
        students.add(new Postgraduate("a","aaa","a@a.com",science));
        students.add(new Postgraduate("x","xx","x@x.com",math));

        Scanner scanner=new Scanner(System.in);
         System.out.println("Enter supervisor name: ");
         String supervisorName=scanner.nextLine();
         System.out.println("Message: ");
         String message=scanner.nextLine();
        Course course=new Course("Course",students);

        Set<Postgraduate> st=course.getPostgraduate(supervisorName);
        (new Notifier<Postgraduate>(st)).doNotifyAll(message);

    }
}
