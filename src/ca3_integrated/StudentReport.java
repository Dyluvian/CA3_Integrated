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