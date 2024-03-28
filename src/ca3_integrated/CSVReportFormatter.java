package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: REPORT FORMATTER FOR CSV FORMAT (CSVREPORTFORMATTER)
// ------------------
// This class contains logic to format reports as CSV files, feeding the Report Formatter interface.
// ------------------
*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVReportFormatter implements ReportFormatter {

    @Override
    public void generateReport(ResultSet resultSet, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            while (resultSet.next()) {
                StringBuilder line = new StringBuilder();
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String value = resultSet.getString(i);
                    if (value != null) {
                        value = value.replace(",", " + ");
                    }
                    line.append(value);
                    if (i < columnCount) {
                        line.append(",");
                    }
                }
                writer.println(line);
            }
            System.out.println("---\nExcellent! The report has been saved as " + filename + ".");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
