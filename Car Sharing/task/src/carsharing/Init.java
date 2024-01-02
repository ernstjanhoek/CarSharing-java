package carsharing;

import java.sql.SQLException;
import java.sql.Statement;

public class Init extends CarSharingApp {
    Init() throws SQLException, ClassNotFoundException {
        this.client = new DBClient(Main.connectionUrl);
        this.companyDao = new CompanyDao(this.client);
        this.carDao = new CarDao(this.client);
        this.customerDao = new CustomerDao(this.client);
    }
    @Override
    String displayMessage() {
        return null;
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        return new Login(this);
    }
    @Override
    void processInput() throws Exception {
        Statement statement0 = this.client.connection.createStatement();
        String createCompanyTable = "CREATE TABLE IF NOT EXISTS company(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) UNIQUE NOT NULL);";
        boolean result0 = statement0.execute(createCompanyTable);
        Statement statement1 = this.client.connection.createStatement();
        String createCarTable = "CREATE TABLE IF NOT EXISTS car(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) UNIQUE NOT NULL," +
                "available BOOLEAN," +
                "company_id INTEGER NOT NULL," +
                "FOREIGN KEY (company_id) REFERENCES company(id)" +
                ");";
        boolean result1 = statement1.execute(createCarTable);
        Statement statement2 = this.client.connection.createStatement();
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS customer(" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) UNIQUE NOT NULL," +
                "rented_car_id INTEGER," +
                "FOREIGN KEY (rented_car_id) REFERENCES car(id)" +
                "ON UPDATE CASCADE" +
                ");";
        boolean result2 = statement2.execute(createCustomerTable);
    }
}