package com.tihonova.lab6.task2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;

import static com.sun.j3d.utils.universe.SimpleUniverse.*;

public class WordArt extends JFrame {

    private static final Color3f TEXT_COLOR = new Color3f(Color.BLACK);;

    private float lightYPos=5.0f;
    private float lightZPos=3.0f;
    private Font font;

    private Material m;
    private JTextField textField;
    private Text3D text;
    private JButton buttonLightColor;
    private Color3f lightColor;

    private Transform3D t;
    private PointLight lightPos;

    private SimpleUniverse view = null;

    public BranchGroup createSceneGraph(Canvas3D canvas) {
        lightColor = new Color3f(Color.GREEN);

        BranchGroup objRoot = new BranchGroup();
        TransformGroup objScale = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setScale(0.3);
        objScale.setTransform(t3d);
        objRoot.addChild(objScale);

        t = new Transform3D();
        TransformGroup transformGroup = new TransformGroup(t);
        objScale.addChild(transformGroup);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        Background bg = new Background(new Color3f(Color.GREEN));
        bg.setApplicationBounds(bounds);
        objScale.addChild(bg);

        m = new Material(TEXT_COLOR, TEXT_COLOR, TEXT_COLOR, new Color3f(Color.WHITE), 1.0f);
        m.setCapability(Material.ALLOW_COMPONENT_WRITE);
        Appearance a = new Appearance();
        m.setLightingEnable(true);
        a.setMaterial(m);
        lightPos = new PointLight();
        lightPos.setEnable(true);
        lightPos.setColor(lightColor);
        lightPos.setAttenuation(1.0f, 0.006f, 0.f);
        lightPos.setPosition(new Point3f(0.0f, lightYPos, lightZPos));
        lightPos.setCapability(Light.ALLOW_COLOR_WRITE);
        lightPos.setCapability(PointLight.ALLOW_STATE_WRITE);
        lightPos.setCapability(PointLight.ALLOW_POSITION_WRITE);
        lightPos.setInfluencingBounds(bounds);
        objScale.addChild(lightPos);

        font=new Font("TestFont", Font.PLAIN, 1);
        Font3D f3d = new Font3D(font, 0.001, new FontExtrusion(new Line2D.Double(0,0,1,1)));

        text = new Text3D(f3d, "Text", new Point3f(0.0f, -0.5f, 0.0f));
        text.setAlignment(Text3D.ALIGN_CENTER);
        text.setCapability(Text3D.ALLOW_STRING_WRITE);
        text.setCapability(Text3D.ALLOW_STRING_READ);
        Shape3D shape3D = new Shape3D(text, a);
        transformGroup.addChild(shape3D);




        objRoot.compile();

        return objRoot;
    }

    public WordArt()
    {
        super("Lab6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        GraphicsConfiguration config = getPreferredConfiguration();

        Canvas3D c = new Canvas3D(config);
        add(c, BorderLayout.CENTER);

        view = new SimpleUniverse(c);
        BranchGroup scene = createSceneGraph(c);
        view.getViewingPlatform().setNominalViewingTransform();
        view.addBranchGraph(scene);

        buttonLightColor=new JButton("Color");
        buttonLightColor.addActionListener(ae -> {
            Color color = JColorChooser.showDialog(null, "Choose Color", lightColor.get());
            if (color != null) {
                lightColor.set(color);
            }
            lightPos.setColor(lightColor);
        });

        c.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lightPos.setPosition(25.0f-e.getX()/10.0f, lightYPos, lightZPos);
            }
        });

        textField=new JTextField();
        textField.setText(text.getString());
        textField.addActionListener(e->{
            text.setString(textField.getText());
        });

        JPanel textAndLightPanel = new JPanel(new BorderLayout());
        textAndLightPanel.add(textField, BorderLayout.CENTER);
        textAndLightPanel.add(buttonLightColor, BorderLayout.EAST);


        add(textAndLightPanel, BorderLayout.SOUTH);
    }



}