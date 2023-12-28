package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String connectionUrl;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //  database URL
        connectionUrl = "jdbc:h2:./src/carsharing/db/" + args[1];
        Scanner scanner = new Scanner(System.in);
        CarSharingApp menu = new Login();
        while (menu.running) {
            try {
                System.out.println(menu.displayMessage());
                menu.selectInput(scanner.nextLine());
                menu.processInput();
                menu = menu.transition();
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
class DBClient extends CarSharingQueries {
    static final String USER = "";
    static final String PASS = "";
    static final String JDBC_DRIVER = "org.h2.Driver";
    private final Connection connection;
    DBClient(String CONNECTION_URL) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection(CONNECTION_URL, USER, PASS);
    }
    public boolean run() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.execute(super.createDBString());
    }
    public ArrayList<CarSharingCompany> selectAll() throws SQLException {
        ArrayList<CarSharingCompany> companyArray = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSetItem = statement.executeQuery(super.getSelectAllString());

        while (resultSetItem.next()) {
            int id = resultSetItem.getInt("id");
            String name = resultSetItem.getString("name");
            CarSharingCompany company = new CarSharingCompany(name);
            company.setId(id);
            companyArray.add(company);
        }
        return companyArray;
    }
    public boolean InsertCompany(String name) throws SQLException {
        Statement statement = connection.createStatement();
        boolean result = statement.execute(super.getInsertDataString(name));
        return result;
    }
}
class CarSharingQueries {
    protected  String createDBString() {
        return "CREATE TABLE IF NOT EXISTS company(" +
                "name VARCHAR(50) UNIQUE NOT NULL, " +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT);";
    }
    protected String getSelectAllString() {
        return  "SELECT id, name FROM company;";
    }
    protected String getInsertDataString(String name) {
        return "INSERT INTO company (name) VALUES (\'" + name + "\');";
    }
}
class CarSharingCompany {
    CarSharingCompany(String name) {
        this.name = name;
    }
    private String name;
    private Integer id;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public String toString() {
        return this.id + ". " + this.name;
    }
}
abstract class CarSharingApp {
    boolean running = true;
    protected DBClient client;
    private String strValue;
    abstract String displayMessage();
    abstract CarSharingApp transition() throws SQLException, ClassNotFoundException;
    abstract void processInput() throws Exception;
    public void selectInput(String inputString) {
        this.strValue = inputString;
    }
    public String getInput() {
        return strValue;
    }
}
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
            MainMenu newMenu = new MainMenu();
            newMenu.client = this.client;
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
class MainMenu extends CarSharingApp {
    public String displayMessage() {
        return "1. Company list\n2. Create a company\n0. Back";
    }
    @Override
    protected CarSharingApp transition() throws SQLException, ClassNotFoundException {
        CarSharingApp newMenu = switch (Integer.parseInt(getInput())) {
            case 2 -> new CreateCompany();
            case 0 -> new Login();
            default -> new MainMenu();
        };
        newMenu.client = this.client;
        return newMenu;
    }
    protected void processInput() throws SQLException {
        if (Integer.parseInt(getInput()) == 1) {
            ArrayList<CarSharingCompany> result = super.client.selectAll();
            if (result.isEmpty()) {
                System.out.println("The company list is empty!");
            } else {
                result.stream().forEach(e -> System.out.println(e.toString()));
            }
        }
    }
}
class CreateCompany extends CarSharingApp {
    public String displayMessage() {
        return "Enter the company name:";
    }
    protected CarSharingApp transition() {
        CarSharingApp newMenu =  new MainMenu();
        newMenu.client = this.client;
        return newMenu;
    }
    protected void processInput() throws SQLException {
        boolean result = client.InsertCompany(getInput());
    }
}