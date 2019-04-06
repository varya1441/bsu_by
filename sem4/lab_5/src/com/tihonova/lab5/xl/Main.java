package com.tihonova.lab5.xl;

import javax.swing.table.AbstractTableModel;

public class Main  {
    private static final int COLUMN_COUNT = 7;
    private static final int ROW_COUNT = 7;

    public static void main(String[] args) {
        DateTableModel model = new DateTableModel(COLUMN_COUNT, ROW_COUNT);
        JTableView view = new JTableView(model);
    }

}
