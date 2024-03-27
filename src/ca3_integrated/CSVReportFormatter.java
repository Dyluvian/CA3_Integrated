package ca3_integrated;

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
                    line.append(resultSet.getString(i));
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
