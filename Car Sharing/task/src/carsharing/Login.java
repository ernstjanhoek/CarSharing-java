package carsharing;

import java.sql.SQLException;

class Login extends CarSharingApp {
    Login() throws SQLException, ClassNotFoundException {
        super.client = new DBClient(Main.connectionUrl);
        super.client.run();
    }
    public String displayMessage() {
        return "1. Log in as a manager\n0. Exit";
    }
    protected CarSharingApp transition() {
        if (Integer.parseInt(getInput()) == 1) {
            MainMenu newMenu = new MainMenu(this);
            newMenu.transferData(this);
            return newMenu;
        }
        return this;
    }
    protected void processInput() {
        switch (Integer.parseInt(getInput())) {
            case 1: {
                break;
            }
            case 0:
                running = false;
        }
    }
}