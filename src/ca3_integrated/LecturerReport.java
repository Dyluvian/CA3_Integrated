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
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // connect to the database
            Statement statement = connection.createStatement();

            String lecturerReportQuery = "SELECT "
                    + "CONCAT(l.first_name, ' ', l.last_name) AS Name, "
                    + // concatenate first name and last name into one name
                    "l.role AS Lecturer_Role, "
                    + "GROUP_CONCAT(DISTINCT m.module_name) AS Modules_Taught, "
                    + // concatenate module names of that lecturer
                    "COUNT(DISTINCT e.student_id) AS Number_of_Students, "
                    + // obtain the number of students they teach. PLEASE NOTE !!!!!!!: This will probably display as 10 for everyone because each has 10 students in my database construction, but the query itself should be fine and I understand that is what matters
                    "GROUP_CONCAT(DISTINCT c.type) AS Types_of_Classes_Taught "
                    + // select the type column from courses and link it to the lecturer
                    "FROM "
                    + "lecturers l "
                    + // from lecturers table
                    "JOIN "
                    + "modules m ON l.lecturer_id = m.lecturer_id "
                    + // join the modules table, referring to lecturer IDs
                    "JOIN "
                    + "courses c ON m.course_id = c.course_id "
                    + // join the courses table, referring to course IDs
                    "LEFT JOIN "
                    + "enrollments e ON m.module_id = e.module_id "
                    + // and left join the enrollments table, based on module IDs
                    "GROUP BY l.lecturer_id"; // use lecturer ID to group the results

            ResultSet resultSet = statement.executeQuery(lecturerReportQuery); // run the above MySQL query

            String fileName = "LecturerReport"; // name the output

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
