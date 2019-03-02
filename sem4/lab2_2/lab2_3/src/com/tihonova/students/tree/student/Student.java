package com.tihonova.students.tree.student;
import java.util.Objects;

public class Student {
    private String name;
    private int course;
    private int group;

    public Student() {
        super();
    }

    public Student(String name, int course, int group) {
        this.name = name;
        this.course = course;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return course == student.course &&
                group == student.group &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, course, group);
    }

    @Override
    public String toString() {
        return  name;
    }
}