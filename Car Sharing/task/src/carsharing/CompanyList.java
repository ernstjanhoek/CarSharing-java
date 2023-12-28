package carsharing;

import java.sql.SQLException;

public class CompanyList extends CarSharingApp {
    CompanyList(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    @Override
    String displayMessage() {
       StringBuilder out = new StringBuilder("Choose a company:\n");
       super.companyArrayList.forEach(e -> out.append(e.toString()).append("\n"));
       return out.toString();
    }
    @Override
    CarSharingApp transition() {
        CarSharingApp newMenu = switch (Integer.parseInt(getInput())) {
            case 0 -> new MainMenu(this);
            default -> new CompanyMenu(this);
        };
        return newMenu;
    }
    @Override
    void processInput() {
        this.setSelectedCompany(companyArrayList.get(Integer.parseInt(getInput())));
    }
}