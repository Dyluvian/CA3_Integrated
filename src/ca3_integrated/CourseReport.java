package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: COURSE REPORT
// ------------------
// The Course Report is one of the three *current* varieties of report, with scope to hopefully add more.
// ------------------
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseReport {

    private final String DB_URL = "jdbc:mysql://localhost/innistown_college"; // Database connections as prescribed. I hope to god they will work
    private final String USERNAME = "pooa2024"; // DB username
    private final String PASSWORD = "pooa2024"; // and pass

    private ReportFormatter reportFormatter;

    public CourseReport(ReportFormatter reportFormatter) {
        this.reportFormatter = reportFormatter;
    }

    public void generateReport(String format) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            String courseReportQuery = "SELECT " +
                            "m.module_name AS Module_Name, " +
                            "c.course_name AS Course_Name, " +
                            "COUNT(DISTINCT e.student_id) AS Number_of_Students_Enrolled, " +
                            "CONCAT(l.first_name, ' ', l.last_name) AS Lecturer_Name, " +
                            "m.room AS Room_Assigned " +
                        "FROM " +
                            "modules m " +
                        "JOIN " +
                            "courses c ON m.course_id = c.course_id " +
                        "JOIN " +
                            "enrollments e ON m.module_id = e.module_id " +
                        "JOIN " +
                            "lecturers l ON m.lecturer_id = l.lecturer_id " +
                        "GROUP BY " +
                            "m.module_id " +
                        "ORDER BY " +
                            "Module_Name";

            ResultSet resultSet = statement.executeQuery(courseReportQuery);

            String fileName = "CourseReport";

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