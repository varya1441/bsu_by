package com.tihonova.countries.runner;

import com.tihonova.countries.app.Application;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
       try {
           Application app = new Application();
           app.setVisible(true);
       }catch (FileNotFoundException e){
           JOptionPane.showMessageDialog(null, "Incorrect file");
       }
    }
}
