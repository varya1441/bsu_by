package com.tihonova.domain;

public class Beast extends Animal {
    private String prey;
    private final int CONSTANTA=23;

    public Beast() {
        super();
        this.prey="none";
    }

    public Beast(String name, String place, String prey) {
        super(name, place);
        this.prey = prey;
    }

    public String getPrey() {
        return prey;
    }

    public void setPrey(String prey) {
        this.prey = prey;
    }

    @Override
    public int massOfPray() {
        return (prey).length()*CONSTANTA;
    }
    @Override
    public String toString() {
        return "Beast{" +
                super.toString()+
                "prey=" + prey +
                '}';
    }
}
