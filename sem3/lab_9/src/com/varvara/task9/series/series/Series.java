package com.varvara.task9.series.series;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Series {
   private int firstElem;
   private int n;
   private double delta;

    public Series(int firstElem, int n, double delta) {
        this.firstElem = firstElem;
        this.n = n;
        this.delta = delta;
    }
    public Series(Series other) {
        this.firstElem = other.firstElem;
        this.delta = other.delta;
        this.n = other.n;
    }

    public void setFirstElem(int firstElem) {
        this.firstElem = firstElem;
    }

    public int getFirstElem() {
        return firstElem;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }



    abstract public double getElem(int firstElem);
    public double getSum(){
        double sum=0.0;
        for(int i=0;i<n;i++){
            sum+=this.getElem(i);
        }
        return sum;
    }

    @Override
    public String toString()
    {
        StringBuffer str=new StringBuffer(" ");

        for(int i=0;i<n;i++){
            str.append(this.getElem(i)+" ");
        }
        return str.toString();
    }
    public void toFile(File file)throws IOException {

        PrintWriter printWriter=new PrintWriter(file);
        printWriter.write(this.toString()+"\n");
        printWriter.write(String.format("%.2f",getSum()));
        printWriter.close();
    }

}
