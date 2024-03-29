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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TextReportFormatter implements ReportFormatter {

    @Override
    public void generateReport(ResultSet resultSet, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                writer.print(metaData.getColumnLabel(i));
                if (i < columnCount) {
                    writer.print("\t");
                }
            }
            writer.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String data = resultSet.getString(i).trim();
                    writer.print(data);
                    if (i < columnCount) {
                        writer.print("\t");
                    }
                }
                writer.println();
            }
            System.out.println("---\nExcellent! The report has been saved as " + filename + ".");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}