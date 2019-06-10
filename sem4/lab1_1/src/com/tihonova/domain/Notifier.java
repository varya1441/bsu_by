package com.tihonova.domain;

import java.util.HashSet;
import java.util.Set;

public class Notifier <T extends Notifiable>{
   private Set<T> notify;

    public Notifier(Set<T> notify) {
        this.notify = notify;
    }

    public void doNotifyAll(String m){
       for (Notifiable n:
            notify) {
        n.notify(m);
       }
   }
}
