package com.tihonova.students.tree.dialog;

import com.tihonova.students.tree.student.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.zip.DataFormatException;

public class StudentDialog extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField courseField;
    private JTextField groupField;
    private JTextField nameField;
    private JLabel course;
    private JLabel group;
    private JLabel name;
    private Student student;
    private JDialog dialog;
    private boolean ok;

    public Student getStudent() {
        return student;
    }


    public StudentDialog(Frame owner, Student oldOne) {
        super(owner, true);
        dialog = this;
        setSize(300, 300);
        setTitle("Edit");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        if (oldOne == null) {
            student = new Student();
            courseField = new JTextField("", 15);
            groupField = new JTextField("", 15);
            nameField = new JTextField("", 15);
        } else {
            this.student = oldOne;
            nameField = new JTextField(oldOne.getName(), 15);
            courseField = new JTextField(Integer.toString(oldOne.getCourse()), 15);
            groupField = new JTextField(Integer.toString(oldOne.getGroup()), 15);
        }

        ok = false;


        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.CENTER);

        contentPane = new JPanel();
        this.add(contentPane, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(3, 2));

        course = new JLabel("Course");
        panel.add(course);
        panel.add(courseField);

        group = new JLabel("Group");
        panel.add(group);
        panel.add(groupField);

        name = new JLabel("Name");
        panel.add(name);
        panel.add(nameField);
        buttonOK = new JButton("Ok");
        contentPane.add(buttonOK);

        buttonOK.addActionListener(this::actionPerformed);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonOK) {
            try {
                if (courseField.getText().equals("") || nameField.getText().equals("") || groupField.getText().equals(""))
                    throw new DataFormatException("Set name!");
                ok = true;
                student.setName(nameField.getText());
                student.setCourse(Integer.parseInt(courseField.getText()));
                student.setGroup(Integer.parseInt(groupField.getText()));

                dialog.setVisible(false);
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, err, "Error!", JOptionPane.PLAIN_MESSAGE);
            } catch (DataFormatException | IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this, err.getMessage(), "Error!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public boolean isOk() {
        return ok;
    }

}
