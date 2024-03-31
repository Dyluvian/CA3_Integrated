package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: LECTURER REPORT
// ------------------
// The Lecturer Report is one of the three *current* varieties of report, with scope to hopefully add more.
// Chatting about interpretations, I have made some last minute modifications to the handling of lecturer reports.
// Instead of printing reports for every single lecturer, it will print only one lecturer's data at a time.
// It will request a lecturer ID to do so in the case of an Office user, or a security password in the case of a Lecturer user. These fields both exist in the MySQL database.
// Just repeating: NOT the password for the Java user, but the password in the Lecturers csv file for the database.
// This is all based on the distinction between the requests for "The name of EVERY module" for Course Report and "The name of THE lecturer" for this one.
// That's defensible to me based on the requirements, but if you'd like to see it print every lecturer's data at once, previous iterations of this file on GitHub showcase that.
// Hope it makes sense.
// Also, there is a simple semester check.
// PLEASE NOTE that half of the lecturers are not active this semester (maybe not very realistic); only half of them have students and classes! Try with multiple.
// ------------------
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerReport {

    private final String DB_URL = "jdbc:mysql://localhost/innistown_college"; // Database connections as prescribed. I hope to god they will work
    private final String USERNAME = "pooa2024"; // DB username
    private final String PASSWORD = "pooa2024"; // and pass

    private ReportFormatter reportFormatter;

    public LecturerReport(ReportFormatter reportFormatter) {
        this.reportFormatter = reportFormatter;
    }

    public void generateReport(String format, String authType, String authInfo) {
        try (
                 Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) { // connect to the database
            String authenticationQuery;
            String lecturerReportQuery;

            // argh, I never want to look at this query again! Very last bit written at 2h before deadline. Kilroy was here and all that. Peace and love
            if ("lecturerID".equalsIgnoreCase(authType)) { // Variation for Office users, reliant on IDs
                authenticationQuery = "SELECT * FROM lecturers WHERE lecturer_id = ?";
                lecturerReportQuery = "SELECT "
                        + "CONCAT(l.first_name, ' ', l.last_name) AS Name, " // Concatenate first name and last name into one name
                        + "l.role AS Lecturer_Role, "
                        + "GROUP_CONCAT(DISTINCT m.module_name) AS Modules_Taught_This_Semester, " // Concatenate module names of that lecturer
                        + "COUNT(DISTINCT e.student_id) AS Number_of_Students, " // Obtain the number of students they teach
                        + "l.types_of_classes AS Types_of_Classes_Taught " // Select the types_of_classes column from lecturers table
                        + "FROM "
                        + "lecturers l " // From lecturers table
                        + "LEFT JOIN "
                        + "modules m ON l.lecturer_id = m.lecturer_id AND m.current_semester = true " // Left join the modules table and include semester check
                        + "LEFT JOIN "
                        + "courses c ON m.course_id = c.course_id " // Left join the courses table, referring to course IDs
                        + "LEFT JOIN "
                        + "enrollments e ON m.module_id = e.module_id " // Left join the enrollments table, based on module IDs
                        + "WHERE l.lecturer_id = ?";
            } else if ("password".equalsIgnoreCase(authType)) { // Secondary variation for Lecturers themselves, reliant on passwords
                authenticationQuery = "SELECT * FROM lecturers WHERE password = ?";
                lecturerReportQuery = "SELECT "
                        + "CONCAT(l.first_name, ' ', l.last_name) AS Name, "
                        + "l.role AS Lecturer_Role, "
                        + "GROUP_CONCAT(DISTINCT m.module_name) AS Modules_Taught_This_Semester, "
                        + "COUNT(DISTINCT e.student_id) AS Number_of_Students, "
                        + "l.types_of_classes AS Types_of_Classes_Taught "
                        + "FROM "
                        + "lecturers l "
                        + "LEFT JOIN "
                        + "modules m ON l.lecturer_id = m.lecturer_id AND m.current_semester = true " // Left join the modules table and include semester check
                        + "LEFT JOIN "
                        + "courses c ON m.course_id = c.course_id " // Left join the courses table, referring to course IDs
                        + "LEFT JOIN "
                        + "enrollments e ON m.module_id = e.module_id " // Left join the enrollments table, based on module IDs
                        + "WHERE l.password = ? "
                        + "GROUP BY l.password, l.first_name, l.last_name, l.role, l.types_of_classes"; // Include types_of_classes in the GROUP BY clause
            } else {
                System.out.println("---\nSomething has gone wrong with the authentication process.");
                return;
            }

            try ( PreparedStatement authStatement = connection.prepareStatement(authenticationQuery)) {
                authStatement.setString(1, authInfo);
                ResultSet authenticationResult = authStatement.executeQuery();
                if (!authenticationResult.next()) {
                    System.out.println("---\nUnfortunately, that lecturer could not be authenticated.");
                    return;
                }
            }

            try ( PreparedStatement reportStatement = connection.prepareStatement(lecturerReportQuery)) {
                reportStatement.setString(1, authInfo);
                ResultSet resultSet = reportStatement.executeQuery(); // run the above MySQL query

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

                reportFormatter.generateReport(resultSet, fileName); // generate the report

                resultSet.close(); // and close everything out
                connection.close();
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("28000")) { // if the credentials do not work...
                System.out.println("---\nUnfortunately, those database credentials could not be validated."); // take issue
            } else {
                e.printStackTrace();
            }
        }
    }
}
