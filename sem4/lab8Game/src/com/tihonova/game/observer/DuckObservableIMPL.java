package com.tihonova.game.observer;

import java.util.ArrayList;
import java.util.List;

public class DuckObservableIMPL<T> implements Observable<T> {


    private List<Observer> observers= new ArrayList<>();

    @Override
    public void registerObserver(Observer<T> o) {

    }

    @Override
    public void removeObserver(Observer<T> o) {

    }

    @Override
    public void notifyObservers(T object) {

    }
}
