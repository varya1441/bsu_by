package by.tihonova.javatr.application;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.DataFormatException;

public class Group extends JDialog implements ActionListener {
    private Set<Toy> toys;
    private JTextField group;
    private static ChildrenGroup childrenGroup;
    private JButton enter;
    private JDialog dialog;
    private JLabel labelName, labelCost, labelChildrenGroup;

    public Group(Frame owner) {
        super(owner, true);
        dialog = this;
        setSize(300, 150);
        setTitle("Group");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        enter = new JButton("Enter");
        this.add(panel, BorderLayout.CENTER);

        labelChildrenGroup = new JLabel("Name");
        group = new JTextField("", 15);
        panel.add(labelChildrenGroup);
        panel.add(group);

        this.add(enter, BorderLayout.NORTH);
        enter.addActionListener(this::actionPerformed);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Set<Toy> newToys = new TreeSet<>();
        if (e.getSource() == enter) {
            try {
                if (group.getText().equals(""))
                    throw new DataFormatException("Set name!");
                childrenGroup = ChildrenGroup.valueOf(group.getText().toUpperCase());

                dialog.setVisible(false);
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, err, "Error!", JOptionPane.PLAIN_MESSAGE);
            } catch (DataFormatException | IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this, err.getMessage(), "Error!", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }
    public static ChildrenGroup getChildrenGroup() {
        return childrenGroup;
    }
}
