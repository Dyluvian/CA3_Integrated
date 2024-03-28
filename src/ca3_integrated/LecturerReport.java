package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: LECTURER REPORT
// ------------------
// The Lecturer Report is one of the three *current* varieties of report, with scope to hopefully add more.
// ------------------
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LecturerReport {

    private final String DB_URL = "jdbc:mysql://localhost/innistown_college"; // Database connections as prescribed. I hope to god they will work
    private final String USERNAME = "pooa2024"; // DB username
    private final String PASSWORD = "pooa2024"; // and pass

    private ReportFormatter reportFormatter;

    public LecturerReport(ReportFormatter reportFormatter) {
        this.reportFormatter = reportFormatter;
    }
    

    public void generateReport(String format) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            
            String lecturerReportQuery = "SELECT " +
                            "CONCAT(l.first_name, ' ', l.last_name) AS Lecturer_Name, " +
                            "l.role AS Lecturer_Role, " +
                            "GROUP_CONCAT(DISTINCT m.module_name) AS Modules_Taught, " +
                            "COUNT(DISTINCT e.student_id) AS Number_of_Students, " +
                            "l.types_of_classes AS Types_of_Classes_Taught " +
                        "FROM " +
                            "lecturers l " +
                        "JOIN " +
                            "modules m ON l.lecturer_id = m.lecturer_id " +
                        "LEFT JOIN " +
                            "enrollments e ON m.module_id = e.module_id " +
                        "GROUP BY " +
                            "l.lecturer_id";

            ResultSet resultSet = statement.executeQuery(lecturerReportQuery);

            String fileName = "LecturerReport";

            switch (format) {
                case "csv":
                    fileName += ".csv";
                    break;
                case "txt":
                    fileName += ".txt";
                    break;
                case "console":
                    break;
                default:
                    System.out.println("NOT VALID FORMAT");
                    return;
            }

            reportFormatter.generateReport(resultSet, fileName);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}