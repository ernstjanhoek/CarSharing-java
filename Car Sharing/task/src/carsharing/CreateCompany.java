package carsharing;

import java.sql.SQLException;

class CreateCompany extends CarSharingApp {
    CreateCompany(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    public String displayMessage() {
        return "Enter the company name:";
    }
    protected CarSharingApp transition() {
        CarSharingApp newMenu = new ManagerMenu(this);
        return newMenu;
    }
    protected void processInput() throws SQLException {
        this.companyDao.add(new Company(getInput()));
    }
}