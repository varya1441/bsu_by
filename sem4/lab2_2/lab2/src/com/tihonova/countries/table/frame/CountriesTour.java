package com.tihonova.countries.table.frame;

import com.tihonova.countries.table.dialog.Dialog;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class CountriesTour extends JPanel {
    private Map<String, Pair<String, ImageIcon>> countries;
    private JTable table;
    private Frame owner;
    private DefaultTableModel data;

    public CountriesTour(Frame owner,Map<String, Pair<String, ImageIcon>> countries) {
        super();
        this.owner=owner;
        setLayout(new BorderLayout());
        setSize(500, 500);

        this.countries = countries;
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(700, 500));
        table.setRowHeight(55);
        this.add(new JScrollPane(table));
        data=new DefaultTableModel(new String[]{"Flag","Country","Cost","Trip"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(row==0) {return false;}
                return super.isCellEditable(row,column);
            }

            @Override
            public Object getValueAt(int row, int column) {
                return super.getValueAt(row, column);
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {

                if(column == 2) {//summ
                    int oldVal = Integer.valueOf((Integer) super.getValueAt(row, column));
                    int newVal = (Integer)aValue;
                    int oldSum = Integer.valueOf((Integer) getValueAt(0, 2));
                    System.out.println(oldSum + "");
                    int newSum = oldSum - oldVal + newVal;
                    super.setValueAt(newSum, 0, 2);
                }
                else if(column == 3){//check
                    boolean value = (Boolean) aValue;
                    if(value == false){
                        int oldSum = Integer.valueOf((Integer) getValueAt(0, 2));
                        int newVal = Integer.valueOf((Integer) getValueAt(row, column - 1));
                        int newSum = oldSum - newVal;
                        super.setValueAt(newSum, 0, 2);
                    }
                    else if(value){
                        int oldSum = Integer.valueOf((Integer) getValueAt(0, 2));
                        int newVal = Integer.valueOf((Integer) getValueAt(row, column - 1));
                        int newSum = oldSum + newVal;
                        super.setValueAt(newSum, 0, 2);
                    }
                }

                super.setValueAt(aValue, row, column);
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
               switch (columnIndex){
                   case 0: return ImageIcon.class;
                   case 1:return String.class;
                   case 2:return Integer.class;
                   case 3:return Boolean.class;
                   default:return Integer.class;
               }
            }
        };
        table.setModel(data);
        int cost=100;
        data.addRow(new Object[]{"","Sum",0,false});
        for (Map.Entry<String,Pair<String,ImageIcon>> c:countries.entrySet()
             ) {
            data.addRow(new Object[]{countries.get(c.getKey()).getValue(),c.getKey(),cost++,false});
        }
        setPreferredSize(new Dimension(600, 550));
        setMinimumSize(new Dimension(600, 400));
    }

    public void addNewTour() {
        com.tihonova.countries.table.dialog.Dialog dialog = new Dialog(owner,countries);
        dialog.setVisible(true);
        if (dialog.isOk()) {
            try {

                data.addRow(dialog.getData());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Exception");
            }
        }

    }

    public void getCost() {
        int cost=0;
        for (int i = 0; i <data.getColumnCount() ; i++) {
            if((Boolean) data.getValueAt(i,3)){
                cost+=(Integer)data.getValueAt(i,2);
            }
        }
        data.setValueAt(cost,0,2);
    }


}
