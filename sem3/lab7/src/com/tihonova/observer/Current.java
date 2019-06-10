package com.tihonova.observer;

import java.util.LinkedList;
import java.util.List;

public class Current<T> implements Observable<T> {

    private List<Observer> observers= new LinkedList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(T object) {
        for (Observer observer : observers)
            observer.update(object);
    }
}