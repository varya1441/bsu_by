package com.tihonova.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Cinema implements Comparable<Cinema> {
    private int seats;
    private String name;
    private ArrayList<String> films;

    public Cinema() {
        this.seats = 0;
        this.name = "noname";
        this.films = null;
    }

    public Cinema(int seats, String name, ArrayList<String> films) {
        this.seats = seats;
        this.name = name;
        this.films = films;
    }

    public void setFilms(ArrayList<String> films) {
        this.films = films;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return seats == cinema.seats &&
                Objects.equals(name, cinema.name) &&
                Objects.equals(films, cinema.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seats, name, films);
    }

    @Override
    public int compareTo(Cinema o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "seats=" + seats +
                ", name='" + name + '\'' +
                ", films=" + films +
                '}';
    }
}
