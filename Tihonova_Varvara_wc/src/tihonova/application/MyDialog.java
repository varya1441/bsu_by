package tihonova.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.DataFormatException;

public class MyDialog extends JDialog implements ActionListener {
    private JPanel panel, bottomPanel;
    private JTextField enteredColor;
    private String color;
    private JButton enter;
    private JDialog dialog;
    private JLabel labelName;

    public MyDialog(Frame owner) {
        super(owner, true);
        dialog = this;
        setSize(300, 100);
        setTitle("Group");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        bottomPanel = new JPanel();
        enter = new JButton("Enter");

        this.add(panel, BorderLayout.CENTER);
        bottomPanel.setLayout(new BorderLayout());
        labelName = new JLabel("Cost");
        enteredColor = new JTextField("", 15);
        panel.add(labelName);
        panel.add(enteredColor);
        bottomPanel.add(enter);

        this.add(bottomPanel, BorderLayout.SOUTH);
        enter.addActionListener(this::actionPerformed);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == enter) {
            try {
                if (enteredColor.getText().equals(""))
                    throw new DataFormatException("Set name!");
                color = enteredColor.getText();

                dialog.setVisible(false);
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, err, "Error!", JOptionPane.PLAIN_MESSAGE);
            } catch (DataFormatException | IllegalArgumentException err) {
                JOptionPane.showMessageDialog(this, err.getMessage(), "Error!", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }

    public String getColor() {
        return color;
    }
}