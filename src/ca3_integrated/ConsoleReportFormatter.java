package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: REPORT FORMATTER FOR CONSOLE (CONSOLEREPORTFORMATTER)
// ------------------
// This class contains logic to format reports printed to the console, feeding the Report Formatter interface.
// ------------------
*/

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleReportFormatter implements ReportFormatter {

@Override
public void generateReport(ResultSet resultSet, String filename) {
    try {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnHeader = metaData.getColumnLabel(i);
            System.out.print(columnHeader);
            if (i < columnCount) {
                System.out.print(" / ");
            }
        }
        System.out.println();
        List<String[]> dataRows = new ArrayList<>();
        while (resultSet.next()) {
            String[] rowData = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                String value = resultSet.getString(i);
                if (value == null) {
                    rowData[i - 1] = "None";
                } else {
                    rowData[i - 1] = value.trim();
                }
            }
            dataRows.add(rowData);
        }
        int[] columnWidths = new int[columnCount];
        for (String[] rowData : dataRows) {
            for (int i = 0; i < columnCount; i++) {
                columnWidths[i] = Math.max(columnWidths[i], rowData[i].length());
            }
        }
        for (String[] rowData : dataRows) {
            for (int i = 0; i < columnCount; i++) {
                System.out.printf("%-" + (columnWidths[i] + 2) + "s", rowData[i]);
                if (i < columnCount - 1) {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println("---\nExcellent! The report has been printed to the console.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}