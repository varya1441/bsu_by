package com.tihonova.diagram.runner;

import com.tihonova.diagram.application.Application;
import org.jfree.ui.RefineryUtilities;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Runner {
    public static void main( String[ ] args ) throws IOException, ParseException {
           Application demo = new Application("Mobile Sales");
           demo.setSize(560, 367);
           RefineryUtilities.centerFrameOnScreen(demo);
           demo.setVisible(true);

    }
}
