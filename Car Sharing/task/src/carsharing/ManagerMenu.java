package carsharing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class MainMenu extends CarSharingApp {
    MainMenu(CarSharingApp otherObject) {
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
            case 0 -> new Login();
            default -> this;
        };
        newMenu.transferData(this);
        return newMenu;
    }

    protected void processInput() throws Exception {
        if (Integer.parseInt(getInput()) == 1) {
            this.companyArrayList = this.selectAll();
            if (this.companyArrayList.isEmpty()) {
                throw new Exception("The company list is empty!");
            } else {
                this.companyArrayList.stream().forEach(e -> System.out.println(e.toString()));
            }
        }
    }

    public ArrayList<Company> selectAll() throws SQLException {
        ArrayList<Company> companyArray = new ArrayList<>();
        Statement statement = super.client.connection.createStatement();
        String selectAll = "SELECT id, name FROM company;";
        ResultSet resultSetItem = statement.executeQuery(selectAll);

        while (resultSetItem.next()) {
            int id = resultSetItem.getInt("id");
            String name = resultSetItem.getString("name");
            Company company = new Company(name);
            company.setId(id);
            companyArray.add(company);
        }
        return companyArray;
    }
}