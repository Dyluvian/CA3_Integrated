package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: MYSQL CONNECTOR
// ------------------
// 
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
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void generateReport(ResultSet resultSet, String fileName, ReportFormatter formatter) {
        formatter.generateReport(resultSet, fileName);
    }
}
