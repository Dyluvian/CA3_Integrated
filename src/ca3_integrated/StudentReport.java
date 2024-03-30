package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: STUDENT REPORT
// ------------------
// The Student Report is one of the three *current* varieties of report, with scope to hopefully add more.
// ------------------
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentReport {

    private final String DB_URL = "jdbc:mysql://localhost/innistown_college"; // Database connections as prescribed. I hope to god they will work
    private final String USERNAME = "pooa2024"; // DB username
    private final String PASSWORD = "pooa2024"; // and pass

    private ReportFormatter reportFormatter;

    public StudentReport(ReportFormatter reportFormatter) {
        this.reportFormatter = reportFormatter;
    }

    public void generateReport(String format) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // connect to the database
            Statement statement = connection.createStatement();

            String studentReportQuery = "SELECT " // this query is a bit disgusting but I think it works
                    + "CONCAT(s.first_name, ' ', s.last_name) AS Name, " // concatenate the first and last names of the student into one name
                    + "s.student_number AS Student_Number, " // retrieve the student number of the students
                    + "c.course_name AS Course_Name, " // and the course name of the courses
                    + "GROUP_CONCAT(DISTINCT m.module_name) AS Modules_Enrolled, " // concatenate the names of modules the student is enrolled in
                    + "COALESCE(m_completed.Modules_Completed, 'None') AS Modules_Completed, " // retrieve the completed modules of the student
                    + "GROUP_CONCAT(DISTINCT m_repeat.module_name) AS Modules_To_Repeat " // concatenate the names of those modules into one
                    + "FROM "
                    + "students s " // select students
                    + "JOIN "
                    + "courses c ON s.course_id = c.course_id " // join courses via IDs
                    + "LEFT JOIN "
                    + "enrollments e ON s.student_id = e.student_id " // left join enrollments, using student IDs
                    + "LEFT JOIN "
                    + "modules m ON e.module_id = m.module_id " // left join modules, using module IDs
                    + "LEFT JOIN "
                    + "grades g ON e.enrollment_id = g.enrollment_id " // left join grades, using enrollment IDs
                    + "LEFT JOIN "
                    + "(SELECT e.student_id, GROUP_CONCAT(DISTINCT CONCAT(m.module_name, ' (Grade: ', g.grade, ')')) AS Modules_Completed " // obtain the completed modules
                    + " FROM enrollments e "
                    + " JOIN modules m ON e.module_id = m.module_id "
                    + " JOIN grades g ON e.enrollment_id = g.enrollment_id "
                    + " WHERE e.status = 'Completed' "
                    + " GROUP BY e.student_id) m_completed ON s.student_id = m_completed.student_id "
                    + "LEFT JOIN "
                    + "(SELECT e.student_id, m.module_name " // obtain the modules to complete
                    + " FROM enrollments e "
                    + " JOIN modules m ON e.module_id = m.module_id "
                    + " WHERE e.status = 'Needs to repeat') m_repeat ON s.student_id = m_repeat.student_id "
                    + "GROUP BY "
                    + "s.student_id"; // and group all by the student ID

            ResultSet resultSet = statement.executeQuery(studentReportQuery); // run the above MySQL query

            String fileName = "StudentReport"; // name the output

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
