package carsharing;

import java.sql.SQLException;

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
        Car localCar = new Car(getInput());
        localCar.setCompanyId(getSelectedCompany().getId());
        carDao.add(localCar);
        System.out.println("The car was added!");
    }
}