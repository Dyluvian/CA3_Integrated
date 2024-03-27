package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// INTERFACE: REPORT FORMATTER
// ------------------
// 
// ------------------
*/

import java.sql.ResultSet;

public interface ReportFormatter {
    void generateReport(ResultSet resultSet, String fileName);
}
