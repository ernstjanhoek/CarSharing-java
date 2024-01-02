package carsharing;

import java.sql.SQLException;

class ManagerMenu extends CarSharingApp {
    ManagerMenu(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    public String displayMessage() {
        return "1. Company list\n2. Create a company\n0. Back";
    }
    @Override
    protected CarSharingApp transition() throws SQLException, ClassNotFoundException {
        CarSharingApp newMenu = switch (Integer.parseInt(getInput())) {
            case 1 -> new CompanyList(this);
            case 2 -> new CreateCompany(this);
            case 0 -> new Login(this);
            default -> this;
        };
        newMenu.transferData(this);
        return newMenu;
    }
    protected void processInput() throws Exception {
        if (Integer.parseInt(getInput()) == 1) {
            this.companyArrayList = this.companyDao.findAll();
            if (this.companyArrayList.isEmpty()) {
                throw new Exception("The company list is empty!");
            } else {
                this.companyArrayList.stream().forEach(e -> System.out.println(e.toString()));
            }
        }
    }
}