package com.tihonova.lab5.xl;

import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTableModel extends DefaultTableModel {
    private static int rec = 0;
    private static int recWarn=-50;

    private String datePatternString = "(((0[1-9]|[12][0-9]|3[01])\\-(0[13578]|10|12))|((0[1-9]|[12][0-9]|30)\\-(0[469]|11))|((0[1-9]|[12][0-9])\\-02))\\-[1-9]\\d{3}";
    private String cellPatternString;

    private Pattern datePattern = Pattern.compile("=?(?<date>"+datePatternString+")");
    private Pattern dateOperationPattern = Pattern.compile("=(?<date>"+datePatternString+")(?<const>[+\\-]\\d+)");
    private Pattern cellPattern;
    private Pattern cellOperationPattern;
    private Pattern minOperationPattern = Pattern.compile("=MIN\\((.*)\\)");
    private Pattern maxOperationPattern = Pattern.compile("=MAX\\((.*)\\)");

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public DateTableModel(int columnCount, int rowCount) {
        String[] rowNum = new String[columnCount+1];
        rowNum[0] = " ";
        for(int i = 0; i < columnCount; i++)
            rowNum[i+1] = Character.toString((char)((int)'A'+i));
        cellPatternString = "["+rowNum[1]+"-"+rowNum[columnCount-1]+"]\\d+";
        cellPattern = Pattern.compile("=(?<cell>"+cellPatternString+")");
        cellOperationPattern = Pattern.compile("=(?<cell>"+cellPatternString+")(?<const>[+\\-]\\d+)");
        setColumnIdentifiers(rowNum);
        setRowCount(rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(column == 0) return false;
        return super.isCellEditable(row, column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    private int pairHash(int x, int y) {
        return 10007*x + y;
    }

    public String calculateCell(String value, int row, int col, Set<Integer> history) {
        if (value == null) return "EMPTY!";
        if (value.equals("")) return "";
        if (history.contains(pairHash(row, col))||rec<recWarn)
            return "RECURSIVE!";
        rec+=row+col;
        history.add(pairHash(row, col));

        Matcher dateMatcher = datePattern.matcher(value);
        Matcher dateOperationMatcher = dateOperationPattern.matcher(value);
        Matcher cellMatcher = cellPattern.matcher(value);
        Matcher cellOperationMatcher = cellOperationPattern.matcher(value);
        Matcher minOperationMatcher = minOperationPattern.matcher(value);
        Matcher maxOperationMatcher = maxOperationPattern.matcher(value);

        try {
            if (dateMatcher.matches()) {
                return dateFormat.format(dateFormat.parse(dateMatcher.group("date")));
            } else if (dateOperationMatcher.matches()) {

                Date date = dateFormat.parse(dateOperationMatcher.group("date"));
                int days = Integer.parseInt(dateOperationMatcher.group("const"));
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, days);

                return dateFormat.format(calendar.getTime());
            } else if (cellMatcher.matches()) {

                int ncol = (cellMatcher.group("cell").charAt(0) - 'A') + 1;
                int nrow = Integer.parseInt(cellMatcher.group("cell").substring(1)) - 1;

                return calculateCell((String) getValueAt(nrow, ncol), nrow, ncol, history);
            } else if (cellOperationMatcher.matches()) {

                int ncol = (cellOperationMatcher.group("cell").charAt(0) - 'A') + 1;
                int nrow = Integer.parseInt(cellOperationMatcher.group("cell").substring(1)) - 1;

                return calculateCell("=" + calculateCell((String) getValueAt(nrow, ncol), nrow, ncol, history)
                        + cellOperationMatcher.group("const"), nrow, ncol,  new HashSet<Integer>());
            } else if (minOperationMatcher.matches()) {
                String[] args = minOperationMatcher.group(1).split(",");
                Date minDate = new Date(Long.MAX_VALUE);
                for (String arg : args) {
                    Date t = dateFormat.parse(calculateCell("=" + arg, -10, -10, new HashSet<>()));
                    if (t.before(minDate))
                        minDate = t;
                }
                return dateFormat.format(minDate);
            } else if (maxOperationMatcher.matches()) {
                String[] args = maxOperationMatcher.group(1).split(",");
                Date maxDate = new Date(Long.MIN_VALUE);
                for (String arg : args) {
                    Date t = dateFormat.parse(calculateCell("=" + arg, -10, -10, new HashSet<>()));
                    if (t.after(maxDate))
                        maxDate = t;
                }
                return dateFormat.format(maxDate);
            } else
                return "Wrong input";
        } catch (ParseException | NumberFormatException e) {
            return "Wrong input";
        }
    }

}
