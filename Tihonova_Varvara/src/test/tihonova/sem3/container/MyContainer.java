package test.tihonova.sem3.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyContainer<T extends Comparable<T>> extends ArrayList<T> {
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (T temp : this) {
            stringBuffer.append(this.toString());
        }
        return stringBuffer.toString();
    }

    public int find(T elem) {
        return Collections.frequency(this, elem);
    }

    public T search(T elem) {
        ArrayList<T> searchArray = (ArrayList<T>) this.clone();
        Collections.sort(searchArray);
        int index=Collections.binarySearch(searchArray,elem);
        return this.get(index);
    }
    public T findMin(){
        return  Collections.min(this);
    }
    public T findMax(){
        return Collections.max(this);
    }

}
