package carsharing;

import java.sql.*;
import java.util.ArrayList;

class DBClient {
    static final String USER = "";
    static final String PASS = "";
    static final String JDBC_DRIVER = "org.h2.Driver";
    final Connection connection;
    DBClient(String CONNECTION_URL) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
    }
    public boolean run() throws SQLException {
        Statement statement0 = connection.createStatement();
        String createCompanyTable = "CREATE TABLE IF NOT EXISTS company(" +
                "name VARCHAR(50) UNIQUE NOT NULL, " +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT);";
        boolean result0 = statement0.execute(createCompanyTable);
        Statement statement1 = connection.createStatement();
        String createCarTable = "CREATE TABLE IF NOT EXISTS car(" +
                "name VARCHAR(50) UNIQUE NOT NULL," +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "company_id INTEGER NOT NULL," +
                "FOREIGN KEY (company_id) REFERENCES company(id)" +
                ");";
        boolean result1 = statement1.execute(createCarTable);
        return result0  & result1;
    }
}
