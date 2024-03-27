package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: LECTURER REPORT
// ------------------
// 
// ------------------
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LecturerReport {

    private final String DB_URL = "jdbc:mysql://localhost/innistown_college";
    private final String USERNAME = "pooa2024";
    private final String PASSWORD = "pooa2024";

    private ReportFormatter reportFormatter;

    public LecturerReport(ReportFormatter reportFormatter) {
        this.reportFormatter = reportFormatter;
    }
    

    public void generateReport(String format) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            String lecturerReportQuery = "SELECT lecturers.first_name, lecturers.last_name, lecturers.role, lecturers.types_of_classes FROM lecturers";

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