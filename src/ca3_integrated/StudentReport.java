package ca3_integrated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentReport {

    private final String DB_URL = "jdbc:mysql://localhost/innistown_college";
    private final String USERNAME = "pooa2024";
    private final String PASSWORD = "pooa2024";

    private ReportFormatter reportFormatter;

    public StudentReport(ReportFormatter reportFormatter) {
        this.reportFormatter = reportFormatter;
    }
    
    public void generateReport(String format) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            String studentReportQuery = "SELECT students.first_name, students.last_name, students.student_number FROM students";

            ResultSet resultSet = statement.executeQuery(studentReportQuery);

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