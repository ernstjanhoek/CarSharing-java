package carsharing;

import java.sql.SQLException;
import java.sql.Statement;

class CreateCompany extends CarSharingApp {
    CreateCompany(CarSharingApp otherObject) {
        this.transferData(otherObject);
    }
    public String displayMessage() {
        return "Enter the company name:";
    }
    protected CarSharingApp transition() {
        CarSharingApp newMenu = new MainMenu(this);
        return newMenu;
    }
    protected void processInput() throws SQLException {
        boolean result = this.insertCompany(getInput());
    }
    private boolean insertCompany(String name) throws SQLException {
        Statement statement = super.client.connection.createStatement();
        String insertCompany = "INSERT INTO company (name) VALUES (\'" + name + "\');";
        boolean result = statement.execute(insertCompany);
        return result;
    }
}
