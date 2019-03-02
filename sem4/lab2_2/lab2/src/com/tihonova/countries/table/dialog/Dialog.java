package com.tihonova.countries.table.dialog;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.zip.DataFormatException;

public class Dialog extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField countryTextField;
    private JTextField costTextField;
    private JButton pictureButton;
    private JLabel jLabel, jLabel2;
    private JDialog dialog;
    private Map<String, Pair<String, ImageIcon>> m;
    private ImageIcon icon;
    boolean ok;

    public Dialog(Frame owner, Map<String, Pair<String, ImageIcon>> m) {
        super(owner, true);
        dialog = this;
        this.m = m;
        setSize(300, 300);
        setTitle("Edit");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ok = false;
        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.CENTER);
        contentPane = new JPanel();
        this.add(contentPane, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(3, 2));
        buttonOK = new JButton("Ok");


        jLabel = new JLabel("Country");
        countryTextField = new JTextField("", 15);

        panel.add(jLabel);
        panel.add(countryTextField);

        jLabel2 = new JLabel("Cost");
        costTextField = new JTextField("", 15);
        panel.add(jLabel2);
        panel.add(costTextField);

        pictureButton = new JButton("Image");
        panel.add(new JLabel("IMage"));
        panel.add(pictureButton);
        contentPane.add(buttonOK);
        pictureButton.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser("C:\\Users\\varvara\\Documents\\GitHub\\bsu_by\\sem4\\lab2_2\\plain");
            int ret = fileopen.showDialog(null, "Open file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                icon = new ImageIcon(fileopen.getSelectedFile().getPath());
            }
        });
        buttonOK.addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonOK) {
            try {
                if (costTextField.getText().equals(""))
                    throw new DataFormatException("Set name!");
                ok = true;

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

    public Object[] getData() throws NumberFormatException {
        return new Object[]{icon, countryTextField.getText(), Integer.parseInt(costTextField.getText()), false};
    }
}
