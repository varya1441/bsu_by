package com.tihonova.domain;

import java.util.Comparator;

public class MyComparator implements Comparator<Cinema> {
    @Override
    public int compare(Cinema o1, Cinema o2) {
        return Integer.compare(o1.getSeats(),o2.getSeats());
    }
}
