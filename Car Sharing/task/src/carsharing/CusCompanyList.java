package carsharing;

import java.sql.SQLException;

public class CusCompanyList extends CarSharingApp {
    CusCompanyList(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
        StringBuilder out = new StringBuilder("Choose a company:\n");
        super.companyArrayList.forEach(e -> out.append(e.toString()).append("\n"));
        return out.append("0. Back").toString();
    }
    @Override
    CarSharingApp transition() throws SQLException, ClassNotFoundException {
        return switch (Integer.parseInt(getInput())) {
            case 0 -> new CustomerMenu(this);
            default -> new CarList(this);
        };
    }
    @Override
    void processInput() throws Exception {
        int inputInt = Integer.parseInt(getInput());
        if (inputInt > 0) {
            int index = inputInt - 1;
            this.setSelectedCompany(companyArrayList.get(index));
            this.carArrayList = this.carDao.findAllAvailable();
        }
    }
}
