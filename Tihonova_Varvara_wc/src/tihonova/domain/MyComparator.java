package com.tihonova.domain;

import java.util.Comparator;

public class MyComparator implements Comparator<Drink> {
    public int compare(Drink o1, Drink o2) {
        return Double.compare(o1.getCaffeineAmount(),o2.getCaffeineAmount());
    }
}
