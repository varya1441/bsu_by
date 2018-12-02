package com.tihonova.lab11.ts;

import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class CanvasLine {
    private List<Coordinates> coordinatesList;
    private Color color;


    public CanvasLine(Color color) {
        super();
        this.coordinatesList = new ArrayList<Coordinates>();
        this.color = color;
    }

    public void add(int x, int y) {

        this.coordinatesList.add(new Coordinates(x, y));
    }

    public void paint(Graphics g) {
        g.setColor(color);
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        for (int i = 1; i < coordinatesList.size(); i++) {
            g.drawLine(coordinatesList.get(i-1).getX(), coordinatesList.get(i-1).getY(),
                    coordinatesList.get(i).getX(), coordinatesList.get(i).getY());
        }
    }


}