package carsharing;

import java.sql.SQLException;
import java.util.ArrayList;

abstract class CarSharingApp {
    ArrayList<Company> companyArrayList;
    ArrayList<Car> carArrayList;
    private Company selectedCompany;
    private Car selectedCar;
    boolean running = true;
    protected DBClient client;
    private String inputString;
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
    public void setSelectedCompany(Company selectedCompany) {
        this.selectedCompany = selectedCompany;
    }
    public Car getSelectedCar() {
        return selectedCar;
    }
    public Company getSelectedCompany() {
        return selectedCompany;
    }
    public void transferData(CarSharingApp otherObject) {
        this.companyArrayList = otherObject.companyArrayList;
        this.carArrayList = otherObject.carArrayList;
        this.client = otherObject.client;
        this.selectedCar = otherObject.getSelectedCar();
        this.selectedCompany = otherObject.getSelectedCompany();
    }
    abstract String displayMessage();
    abstract CarSharingApp transition() throws SQLException, ClassNotFoundException;
    abstract void processInput() throws Exception;
    public void setInput(String inputString) {
        this.inputString = inputString;
    }
    public String getInput() {
        return inputString;
    }
}