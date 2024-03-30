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
                String columnHeader = metaData.getColumnLabel(i).replace("_", " "); // obtain the headers of each column, without underscores
                columnHeader = columnHeader.trim(); // trim the column header
                writer.print(columnHeader); // now print the trimmed column header
                if (i < columnCount) { // if it's not the final column...
                    writer.print("\t"); // add a tab
                }
            }
            writer.println(); // then continue

            while (resultSet.next()) { // iterate over the result set
                for (int i = 1; i <= columnCount; i++) { // iterate over each column in the current row
                    String data = resultSet.getString(i); // obtain data from the current column
                    if (data == null) { // if the data is null...
                        data = "None"; // set it to "None"
                    } else {
                        data = data.trim(); // trim the data
                        data = data.replace(",", ", "); // add a space after each comma
                    }
                    writer.print(data); // print the data
                    if (i < columnCount) { // if it's not the final column...
                        writer.print("\t"); // add a tab
                    }
                }
                writer.println(); // move to the next line for the next row
            }

            System.out.println("---\nExcellent! The report has been saved as " + filename + "."); // ...we're done
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
