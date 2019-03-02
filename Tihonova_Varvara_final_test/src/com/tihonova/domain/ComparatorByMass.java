package com.tihonova.domain;

import java.util.Comparator;

public class ComparatorByMass implements Comparator<Animal> {
    public int compare(Animal o1, Animal o2) {
        return Integer.compare(o2.massOfPray(),o1.massOfPray());// Double.compare(o1.getCaffeineAmount(),o2.getCaffeineAmount());
    }
}
