package carsharing;

import java.sql.SQLException;
import java.util.Optional;

public class CarList extends CarSharingApp  {
    CarList(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        StringBuilder out = new StringBuilder("Choose a car:\n");
        super.carArrayList.forEach(e -> out.append(e.toString()).append("\n"));
        return out.append("0. Back").toString();
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        CarSharingApp newMenu = new CustomerMenu(this);
        return newMenu;
    }
    @Override
    void processInput() throws Exception {
        int inputInt = Integer.parseInt(getInput());
        if (inputInt > 0) {
            int index = inputInt - 1;
            this.setSelectedCar(carArrayList.get(index));
            this.customerDao.rentCar(this.getSelectedCostumer());
            System.out.println("You rented '" + this.getSelectedCostumer().getCar().getName() + "'");
        }
    }
}