package ca3_integrated;

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
