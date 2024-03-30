package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: STUDENT REPORT
// ------------------
// The Student Report is one of the three *current* varieties of report, with scope to hopefully add more.
// Chatting about interpretations, I have made some last minute modifications to the handling of student reports.
// Instead of printing reports for every single student, it will print only one student's data at a time, and request a student ID to do so.
// This is based on the distinction between the requests for "The name of EVERY module" for Course Report and "The name and student number of THE student" for this one.
// That's defensible to me based on the requirements, but if you'd like to see it print every student's data at once, previous iterations of this file on GitHub showcase that.
// Hope it makes sense.
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

    public void generateReport(String format, int studentID) {
        {
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();

                String checkStudentQuery = "SELECT * FROM students WHERE student_id = " + studentID; // check the validity of the student ID first
                ResultSet checkResult = statement.executeQuery(checkStudentQuery); // check the results of the student ID column

                if (!checkResult.next()) { // if there is nothing in that first resultset...
                    System.out.println("---\nDrat, drat, and double drat! No student with the ID #" + studentID + " was locatable in the database."); // print an error
                    checkResult.close();
                    statement.close();
                    connection.close();
                    return; // then end this process
                }

                String studentReportQuery = "SELECT " // this query is a bit disgusting but I think it works
                        + "CONCAT(s.first_name, ' ', s.last_name) AS Name, " // concatenate the first and last names of the student into one name
                        + "s.student_number AS Student_Number, " // and the course name of the courses
                        + "c.course_name AS Course_Name, " // concatenate the names of modules the student is enrolled in
                        + "GROUP_CONCAT(DISTINCT m.module_name) AS Modules_Enrolled, " // retrieve the completed modules of the student
                        + "COALESCE(m_completed.Modules_Completed, 'None') AS Modules_Completed, " // concatenate the names of those modules into one
                        + "GROUP_CONCAT(DISTINCT m_repeat.module_name) AS Modules_To_Repeat "
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
                        + " WHERE e.status = 'Completed' AND e.student_id = " + studentID + " "
                        + " GROUP BY e.student_id) m_completed ON s.student_id = m_completed.student_id " // obtain the modules to complete
                        + "LEFT JOIN "
                        + "(SELECT e.student_id, m.module_name "
                        + " FROM enrollments e "
                        + " JOIN modules m ON e.module_id = m.module_id "
                        + " WHERE e.status = 'Needs to repeat' AND e.student_id = " + studentID + ") m_repeat ON s.student_id = m_repeat.student_id "
                        + "WHERE s.student_id = " + studentID + " "
                        + "GROUP BY "
                        + "s.student_id"; // and group all by the student ID

                ResultSet resultSet = statement.executeQuery(studentReportQuery);

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
}