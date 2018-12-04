package com.varvara.task9.series.linear;

import com.varvara.task9.series.series.Series;

public class Linear extends Series {

    public Linear(int firstElem, int n, double delta) {
        super(firstElem, n, delta);
    }

    public Linear(Series linear){
        super(linear);
    }
    @Override
    public double getElem(int n) {
        return getFirstElem()+getDelta()*(n);
    }
}
