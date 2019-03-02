package com.tihonova.students.tree.frame;

import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {

    public MyJFrame(){
        super("Tree");

        JPanel panel = new JPanel();
        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);

        StudentsPanel studentsPanel = new StudentsPanel(this);
        panel.add(studentsPanel);

        JPanel jPanel = new JPanel();
        add(jPanel, BorderLayout.SOUTH);



        JButton addStudent = new JButton("Add");
        addStudent.addActionListener(e->{
            studentsPanel.addStudent();
        });
        jPanel.add(addStudent);

        JButton deleteStudent = new JButton("Delete");
        deleteStudent.addActionListener(e->{
            studentsPanel.deleteStudent();
        });
        jPanel.add(deleteStudent);

        JButton refactorStudent = new JButton("Refactor");
        refactorStudent.addActionListener(e->{
            studentsPanel.refactorStudent();
        });
        jPanel.add(refactorStudent);

        setPreferredSize(new Dimension(700, 500));
        setMinimumSize(new Dimension(700, 500));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}