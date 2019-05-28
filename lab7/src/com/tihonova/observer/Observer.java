package com.tihonova.observer;

public interface Observer<E> {
    void update(E object);
}