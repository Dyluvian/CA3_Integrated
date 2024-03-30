package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: REPORT FORMATTER FOR CSV FORMAT (CSVREPORTFORMATTER)
// ------------------
// This class contains logic to format reports as CSV files, feeding the Report Formatter interface.
// They are, of course, comma-separated.
// ------------------
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CSVReportFormatter implements ReportFormatter {

    @Override
    public void generateReport(ResultSet resultSet, String filename) {
        try ( PrintWriter writer = new PrintWriter(filename)) {
            ResultSetMetaData metaData = resultSet.getMetaData(); // obtain metadata from the results
            int columnCount = metaData.getColumnCount(); // obtain the number of columns
            for (int i = 1; i <= columnCount; i++) { // run through each column
                String columnHeader = metaData.getColumnLabel(i).replace("_", " "); // obtain the headers of each column, without underscores
                writer.print(columnHeader); // now print the headers
                if (i < columnCount) { // if it's not the final column...
                    writer.print(","); // ...add a comma
                }
            }
            writer.println(); // then continue

            while (resultSet.next()) {
                StringBuilder line = new StringBuilder(); // to hold the current line
                for (int i = 1; i <= columnCount; i++) { // run through each column again
                    String value = resultSet.getString(i); // obtain whatever it says
                    if (value == null) {
                        value = "None"; // null to "None"
                    } else {
                        value = value.replace("\n", "").replace("\r", ""); // get rid of any stray newlines, this was a problem
                        value = value.replace(",", " + "); // if there are any commas in the input, modify them
                    }
                    line.append(value); // Append data to the line
                    if (i < columnCount) { // if it's not the final column...
                        line.append(","); // ...add a comma
                    }
                }
                writer.println(line); // continue on, until...
            }
            System.out.println("---\nExcellent! The report has been saved as " + filename + "."); // ...we're done
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
