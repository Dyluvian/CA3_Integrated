package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: REPORT FORMATTER FOR CONSOLE (CONSOLEREPORTFORMATTER)
// ------------------
// This class contains logic to format reports printed to the console, feeding the Report Formatter interface.
// The output is designed to look pretty within the console. Please ensure you switch off word wrap to ensure proper display and avoid eyesores.
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
            ResultSetMetaData metaData = resultSet.getMetaData(); // obtain metadata from the results
            int columnCount = metaData.getColumnCount(); // obtain the number of columns
            int[] columnWidths = new int[columnCount]; // hold the widths of each column
            for (int i = 1; i <= columnCount; i++) { // run through each column
                String columnHeader = metaData.getColumnLabel(i).replace("_", " "); // obtain the headers of each column, without underscores
                columnWidths[i - 1] = Math.max(columnHeader.length(), 20); // set the maximum width of each column, with 20 being the minimum for aesthetics
            }

            List<String[]> dataRows = new ArrayList<>(); // store each row of data
            while (resultSet.next()) {
                String[] rowData = new String[columnCount]; // store the active row's data
                for (int i = 1; i <= columnCount; i++) { // run through each column
                    String value = resultSet.getString(i);
                    if (value == null) { // if the current column data contains nothing...
                        rowData[i - 1] = "None"; // ...replace it with "None" to avoid nullpointer exception
                    } else {
                        rowData[i - 1] = value.trim(); // otherwise, clean up by removing any useless characters
                    }
                    columnWidths[i - 1] = Math.max(columnWidths[i - 1], rowData[i - 1].length()); // now the maximum width of the column
                }
                dataRows.add(rowData); // and add the data row
            }

            System.out.print("+");
            for (int i = 0; i < columnCount; i++) { // starting out...
                System.out.print(repeatString("-", columnWidths[i] + 2) + "+"); // give us a horizontal separator made out of dashes
            }
            System.out.println();

            for (int i = 1; i <= columnCount; i++) { // run through each column
                String columnHeader = metaData.getColumnLabel(i).replace("_", " "); // obtain the headers of each column, without underscores
                System.out.printf("| %-" + (columnWidths[i - 1]) + "s ", columnHeader); // print that out with adjustments
            }
            System.out.println("|"); // and keep going, printing a vertical bar

            System.out.print("+"); // before the data section...
            for (int i = 0; i < columnCount; i++) {
                System.out.print(repeatString("-", columnWidths[i] + 2) + "+"); // give us a horizontal separator made out of dashes
            }
            System.out.println();

            for (String[] rowData : dataRows) { // now run through the data
                System.out.print("|");
                for (int i = 0; i < columnCount; i++) { // and run through each column
                    String adjustedData = rowData[i].replaceAll(",", ", "); // and add a space after each comma, if there is a comma
                    System.out.printf(" %-" + (columnWidths[i]) + "s |", adjustedData); // and print the adjusted data
                }
                System.out.println(); // ...and keep going...
            }

            System.out.print("+");
            for (int i = 0; i < columnCount; i++) { // lastly...
                System.out.print(repeatString("-", columnWidths[i] + 2) + "+"); // give us a closing horizontal separator made out of dashes
            }
            System.out.println();

            System.out.println("---\nExcellent! The report has been printed to the console."); // ...we're done
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String repeatString(String str, int n) { // this is for repeating a string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
