package by.tihonova.javatr.application;

import by.tihonova.javatr.domain.childrengroup.ChildrenGroup;
import by.tihonova.javatr.domain.toy.Toy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.DataFormatException;

public class InputDialog extends JDialog implements ActionListener {
    private Toy toy;
    private JLabel labelName, labelCost, labelChildrenGroup;
    private JTextField name, cost, group;
    private JButton enter;
    private JDialog dialog;

    public InputDialog(Frame owner, Toy toy) {
        super(owner, true);
        dialog = this;
        this.toy = toy;
        setSize(300, 300);
        setTitle("Edit");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(3, 2));
        enter = new JButton("Enter");


        // this.add(enter,BorderLayout.SOUTH);
        labelName = new JLabel("Name");
        name = new JTextField("", 15);
        panel.add(labelName);
        panel.add(name);


        labelCost = new JLabel("Cost");
        cost = new JTextField("");
        panel.add(labelCost);
        panel.add(cost);

        labelChildrenGroup = new JLabel("Children group");
        group = new JTextField("");
        panel.add(labelChildrenGroup);
        panel.add(group);

        this.add(enter, BorderLayout.NORTH);
        enter.addActionListener(this::actionPerformed);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            try {
                if (name.getText().equals(""))
                    throw new DataFormatException("Set name!");
                toy.setName(name.getText());
                toy.setCost(Double.parseDouble(cost.getText()));
                toy.setChildrenGroup(ChildrenGroup.valueOf(group.getText().toUpperCase()));
//                dialog.setVisible(false);
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, err, "Error!", JOptionPane.PLAIN_MESSAGE);
            } catch (DataFormatException|IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this, err.getMessage(), "Error!", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }
}


