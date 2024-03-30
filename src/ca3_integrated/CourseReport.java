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
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // connect to the database
            Statement statement = connection.createStatement();

            String courseReportQuery = "SELECT "
                    + "m.module_name AS Module_Name, " // select the module name
                    + "c.course_name AS Course_Name, " // select the course name
                    + "COUNT(DISTINCT e.student_id) AS Number_of_Students_Enrolled, " // count students enrolled
                    + "CONCAT(l.first_name, ' ', l.last_name) AS Lecturer_Name, " // concatenate first and last names of lecturer
                    + "m.room AS Room_Assigned " // use room field in modules
                    + "FROM "
                    + "modules m " // select modules
                    + "JOIN "
                    + "courses c ON m.course_id = c.course_id " // join courses based on course ID
                    + "JOIN "
                    + "enrollments e ON m.module_id = e.module_id " // join enrollments based on module ID
                    + "JOIN "
                    + "lecturers l ON m.lecturer_id = l.lecturer_id " // join lecturers based on lecturer ID
                    + "GROUP BY "
                    + "m.module_id " // group all by module ID
                    + "ORDER BY "
                    + "Module_Name"; // and arrange by module name

            ResultSet resultSet = statement.executeQuery(courseReportQuery); // run the above MySQL query

            String fileName = "CourseReport"; // name the output

            switch (format) { // format the report based on the format requested
                case "csv": // if it's csv...
                    fileName += ".csv"; // append csv to the filename
                    break;
                case "txt": // if it's txt...
                    fileName += ".txt"; // append txt to the filename
                    break;
                case "console": // if it's console...
                    break; // no file, so do nothing
                default: // if it's not valid...
                    System.out.println("---\nUnfortunately, no valid format has been specified."); // print an error
                    return;
            }

            reportFormatter.generateReport(resultSet, fileName); // generate the report...

            resultSet.close(); // and close everything out
            statement.close();
            connection.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("28000")) { // if the credentials do not work...
                System.out.println("---\nUnfortunately, those database credentials could not be validated."); // take issue
            } else {
                e.printStackTrace();
            }
        }
    }
}
