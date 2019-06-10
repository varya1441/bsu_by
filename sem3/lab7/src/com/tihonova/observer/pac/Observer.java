package com.tihonova.observer.pac;

public interface Observer<E> {
    void update(E object);
}