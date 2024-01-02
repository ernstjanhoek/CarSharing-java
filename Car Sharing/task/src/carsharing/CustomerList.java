package carsharing;

import java.sql.SQLException;

public class CustomerList extends CarSharingApp {
    CustomerList(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        StringBuilder out = new StringBuilder("Choose a customer:\n");
        super.customerArrayList.forEach(e -> out.append(e.toString()).append("\n"));
        return out.append("0. Back").toString();
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        return switch (Integer.parseInt(getInput())) {
            case 0 -> new Login(this);
            default -> new CustomerMenu(this);
        };
    }
    @Override
    void processInput() throws Exception {
        int inputInt = Integer.parseInt(getInput());
        if (inputInt > 0) {
            int index = inputInt - 1;
            this.setSelectedCustomer(customerArrayList.get(index));
        }
    }
}