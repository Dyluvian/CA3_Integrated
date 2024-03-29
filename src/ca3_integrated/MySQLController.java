package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: MYSQL CONNECTOR
// ------------------
// The MySQL Connector is like Ronseal: it connects the program to MySQL.
// This code is largely reproduced from material given to us by our lecturer Sam.
// ------------------
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLController {

    private final String DB_URL = "jdbc:mysql://localhost/innistown_college";
    private final String USERNAME = "pooa2024";
    private final String PASSWORD = "pooa2024";

    public ResultSet executeQuery(String query) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // test the above credentials against the database
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("---\nAlas; it's all gone a bit pear-shaped with the database connector! Here is an error message for you: " + e.getMessage());
            return null;
        }
    }

    public void generateReport(ResultSet resultSet, String fileName, ReportFormatter formatter) { // for generating reports
        formatter.generateReport(resultSet, fileName);
    }
}
