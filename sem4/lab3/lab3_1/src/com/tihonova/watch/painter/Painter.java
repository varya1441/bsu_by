package com.tihonova.watch.painter;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Time;

public class Painter extends JPanel {
    private int time;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel app;
    private BufferedImage bufferedImage;

    public Painter() {
        app=this;
        //setSize(500,500);
        setPreferredSize(new Dimension(500,500));

        time=0;
        Timer timer=new Timer(1000,e->{
            time=(time+1)%60;
           // bufferedImage=new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
            repaint();
        });
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.PINK);

        g.fillOval(app.getWidth()/3,app.getWidth()/4,200,200);
        g.setColor(Color.BLACK);
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        g.drawLine(99 + app.getWidth() / 3, 102+app.getWidth()/4, (int) (99 + app.getWidth()/ 3 + 100 * Math.sin(Math.toRadians(time * 6))),
                (int) (102 - 100 * Math.cos(Math.toRadians(time * 6))+app.getWidth()/4));;
    }
}
