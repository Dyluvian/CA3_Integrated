package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: REPORT FORMATTER FOR TXT FORMAT (TEXTREPORTFORMATTER)
// ------------------
// This class contains logic to format reports as TXT files, feeding the Report Formatter interface.
// In the absence of a specific prescribed style, it will output the report in a tab-separated format.
// ------------------
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TextReportFormatter implements ReportFormatter {

    @Override
    public void generateReport(ResultSet resultSet, String filename) {
        try ( PrintWriter writer = new PrintWriter(filename)) {
            ResultSetMetaData metaData = resultSet.getMetaData(); // obtain metadata from the results
            int columnCount = metaData.getColumnCount(); // obtain the number of columns
            int[] columnWidths = new int[columnCount]; // hold the widths of each column
            for (int i = 1; i <= columnCount; i++) { // run through each column
                String columnHeader = metaData.getColumnLabel(i).replace("_", " "); // obtain the headers of each column, without underscores
                columnWidths[i - 1] = Math.max(columnHeader.length(), 20); // set the maximum width of each column, with 20 being the minimum for aesthetics
            }

            for (int i = 1; i <= columnCount; i++) { // run through each column again
                String columnHeader = metaData.getColumnLabel(i).replace("_", " "); // repeat the same process: get header text and remove underscores
                writer.printf("%-" + (columnWidths[i - 1]) + "s", columnHeader); // now print the headers to align with the widths
                if (i < columnCount) { // if it's not the final column...
                    writer.print("\t"); // add a tab
                }
            }
            writer.println(); // then continue

            while (resultSet.next()) { //
                for (int i = 1; i <= columnCount; i++) { // run through each column again
                    String data = resultSet.getString(i); // obtain whatever it says
                    if (data == null) { // if it says nothing...
                        data = "None"; // set the output to "None" to avoid nullpointer exception
                    } else {
                        data = data.trim(); // otherwise, clean up by removing any useless characters
                    }
                    writer.printf("%-" + (columnWidths[i - 1]) + "s", data); // now print the data with some aesthetic tweaks
                    if (i < columnCount) { // if it's not the final column...
                        writer.print("\t"); //  add a tab
                    }
                }
                writer.println(); // continue on, until...
            }

            System.out.println("---\nExcellent! The report has been saved as " + filename + "."); // ...we're done
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
