package carsharing;

import java.sql.SQLException;

public class CustomerMenu extends CarSharingApp {
    CustomerMenu(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        return "1. Rent a car\n2. Return a rented car\n3. My rented car\n0. Back";
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        CarSharingApp newMenu = switch (Integer.parseInt(getInput())) {
            case 1 -> new CusCompanyList(this);
            case 0 -> new Login(this);
            default -> this;
        };
        return newMenu;
    }
    @Override
    void processInput() throws Exception {
        int input = Integer.parseInt(getInput());
        switch (input) {
            case 1:
                this.companyArrayList = companyDao.findAll();
                if (this.companyArrayList.isEmpty()) {
                    throw new Exception("The company list is empty!");
                }
                if (this.getSelectedCostumer().getCar() != null) {
                    throw new Exception("You've already rented a car!");
                }
                break;
            case 2:
                if (this.getSelectedCostumer().getCar() != null) {
                    this.customerDao.returnCar(this.getSelectedCostumer());
                    this.setSelectedCar(null);
                    System.out.println("You've returned a rented car!");
                } else {
                    System.out.println("You didn't rent a car!");
                }
                break;
            case 3:
                if (this.getSelectedCostumer().getCar() != null ) {
                    System.out.println("Your rented car:\n" + this.getSelectedCostumer().getCar().getName());
                    System.out.println("Company:\n" + this.getSelectedCompany().getName());
                } else {
                    System.out.println("You didn't rent a car!");
                }
        }
    }
}