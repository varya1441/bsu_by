package by.tihonova.javatr.domain;

import by.tihonova.javatr.domain.toy.Toy;
import by.tihonova.javatr.exception.CollectionIsEmpty;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

//public class PlayRoom <T extends Toy> extends TreeSet<T> {
//    public void print() {
//        StringBuffer stringBuffer = new StringBuffer();
//        for (T temp : this) {
//            stringBuffer.append(temp.toString());
//        }
//        System.out.println(stringBuffer.toString());
//    }
//
//    public int find(T elem) {
//        return Collections.frequency(this, elem);
//    }
//
//    public void toFile(File file)throws IOException {
//
//        PrintWriter printWriter=new PrintWriter(file);
//        StringBuffer stringBuffer = new StringBuffer();
//        for (T temp : this) {
//            stringBuffer.append(temp.toString());
//        }
//        printWriter.write(stringBuffer.toString()+"\n");
//        printWriter.close();
//    }
//
//    public T findMax() throws CollectionIsEmpty {
//        if (this.isEmpty() || this == null)
//            throw new CollectionIsEmpty();
//        return Collections.max(this);
//    }
//}
