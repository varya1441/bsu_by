package com.tihonova.duck.game;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter@Setter
public class Duck  {
    private int x;
    private int y;
    private ImageIcon picture=new ImageIcon("duck.png");
}
