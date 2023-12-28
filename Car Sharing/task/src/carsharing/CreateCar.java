package carsharing;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateCar extends CarSharingApp {
    CreateCar(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        return "Enter the car name:";
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        CarSharingApp newMenu = new CompanyMenu(this);
        return newMenu;
    }
    @Override
    void processInput() throws Exception {
        boolean result = this.insertCar(getInput());
        if (result) {
            System.out.println("The car was added!");
        }

    }
    private boolean insertCar(String name) throws SQLException {
        Statement statement = super.client.connection.createStatement();
        String insertCompany = "INSERT INTO car (name) VALUES (\'" + name + "\');";
        boolean result = statement.execute(insertCompany);
        return result;
    }
}
