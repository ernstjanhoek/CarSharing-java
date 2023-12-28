package carsharing;

import java.sql.SQLException;

public class CompanyMenu extends CarSharingApp {
    CompanyMenu(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        return "1. Car List\n2. Create a car\n0. Back";
    }

    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    void processInput() throws Exception {

    }
}
