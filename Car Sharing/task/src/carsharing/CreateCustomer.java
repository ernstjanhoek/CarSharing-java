package carsharing;

import java.sql.SQLException;

public class CreateCustomer extends CarSharingApp {
    CreateCustomer(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        return "Enter the customer name:";
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        CarSharingApp newMenu = new Login(this);
        return newMenu;
    }
    @Override
    void processInput() throws Exception {
        this.customerDao.add(new Customer(getInput()));
        System.out.println("The customer was added!");
    }
}