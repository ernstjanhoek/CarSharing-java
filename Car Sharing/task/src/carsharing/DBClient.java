package carsharing;

import java.sql.*;

class DBClient {
    static final String USER = "";
    static final String PASS = "";
    static final String JDBC_DRIVER = "org.h2.Driver";
    final Connection connection;
    DBClient(String CONNECTION_URL) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
        this.connection.setAutoCommit(true);
    }
}
