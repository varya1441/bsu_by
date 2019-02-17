package com.tihonova.domain;

import java.util.Comparator;

public class MyComparator implements Comparator<Animal> {
    public int compare(Animal o1, Animal o2) {
        return 0;// Double.compare(o1.getCaffeineAmount(),o2.getCaffeineAmount());
    }
}
