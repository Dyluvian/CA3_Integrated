package ca3_integrated;

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
                    rowData[i - 1] = resultSet.getString(i).trim();
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