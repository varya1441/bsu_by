package com.varvara.task9.series.exponential;

import com.varvara.task9.series.series.Series;

public class Exponential extends Series {

    public Exponential(int firstElem, int n, double delta) {
        super(firstElem, n, delta);
    }

    public Exponential(Series exponential){
        super(exponential);
    }
    @Override
    public double getElem(int n) { return getFirstElem()*Math.pow(getDelta(),n);
    }
}
