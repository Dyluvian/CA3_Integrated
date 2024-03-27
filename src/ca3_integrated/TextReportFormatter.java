package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: REPORT FORMATTER FOR TXT FORMAT (TEXTREPORTFORMATTER)
// ------------------
// This class contains logic to format reports as TXT files, feeding the Report Formatter interface.
// ------------------
*/

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TextReportFormatter implements ReportFormatter {

    @Override
    public void generateReport(ResultSet resultSet, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            while (resultSet.next()) {
                String data = resultSet.getString(1);
                writer.println(data);
            }
            System.out.println("---\nExcellent! The report has been saved as " + filename + ".");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
