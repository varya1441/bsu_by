package test.tihonova.sem3.container;

import test.tihonova.sem3.domain.Car;
import test.tihonova.sem3.exception.CollectionIsEmpty;

import java.util.ArrayList;
import java.util.Collections;

public class MyContainer<T extends Car> extends ArrayList<T> {
    public void print() {
        StringBuffer stringBuffer = new StringBuffer();
        for (T temp : this) {
            stringBuffer.append(temp.toString());
        }
        System.out.println(stringBuffer.toString());
    }

    public int find(T elem) {
        return Collections.frequency(this, elem);
    }

    public T search(T elem) throws CollectionIsEmpty {
        if (this.isEmpty())
            throw new CollectionIsEmpty();
        MyContainer<T> container = (MyContainer<T>) this.clone();
        Collections.sort(container);
        int index = Collections.binarySearch(container, elem);
        return container.get(index);
    }

    public T findMax() throws CollectionIsEmpty {
        if (this.isEmpty() || this == null)
            throw new CollectionIsEmpty();
        return Collections.max(this);
    }

}
