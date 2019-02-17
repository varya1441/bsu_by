package com.tihonova.domain;

public class Person implements Notifiable{
    private String name;


    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void notify(String m) {
        System.out.println("name "+name+"notification "+m);
    }
}
