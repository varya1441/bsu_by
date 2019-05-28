package com.tihonova.game.observer;

public interface Observable<E> {
    void registerObserver(Observer<E> o);
    void removeObserver(Observer<E> o);
    void notifyObservers(E object);
}