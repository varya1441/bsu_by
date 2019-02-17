package com.tihonova.domain;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private String name;
    private Set<Student> students;

    public Course(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }
    public Set<Postgraduate> getPostgraduate(String nameOfSupervisor){
            Set<Postgraduate> student=new HashSet<>();
        for (Student s:students
             ) {
            if(s instanceof Postgraduate){
                if (((Postgraduate) s).getSupervisor().getName().equals(nameOfSupervisor))
                student.add((Postgraduate) s);
            }
        }
        return student;
    }
}
