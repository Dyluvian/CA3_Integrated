package ca3_integrated;

import java.sql.ResultSet;

public interface ReportFormatter {
    void generateReport(ResultSet resultSet, String fileName);
}
