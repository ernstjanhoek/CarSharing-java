package carsharing;

import java.sql.SQLException;

public class CompanyMenu extends CarSharingApp {
    CompanyMenu(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        return "1. Car list\n2. Create a car\n0. Back";
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        CarSharingApp newMenu = switch (Integer.parseInt(getInput())) {
            case 2 -> new CreateCar(this);
            case 0 -> new ManagerMenu(this);
            default -> this;
        };
        return newMenu;
    }
    @Override
    void processInput() throws Exception {
        if (Integer.parseInt(getInput()) == 1) {
            this.carArrayList = carDao.findAllByCompanyID(this.getSelectedCompany());
            if (this.carArrayList.isEmpty()) {
                throw new Exception("The car list is empty!");
            } else {
                for (int i = 0; i < this.carArrayList.size(); i++) {
                    System.out.println(i + 1 + ". " + "" + this.carArrayList.get(i).getName());
                }
            }
        }
    }
}