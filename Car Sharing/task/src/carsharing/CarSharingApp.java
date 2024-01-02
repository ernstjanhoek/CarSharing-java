package carsharing;

import java.sql.SQLException;
import java.util.ArrayList;

abstract class CarSharingApp {
    ArrayList<Company> companyArrayList = new ArrayList<>();
    ArrayList<Car> carArrayList = new ArrayList<>();
    ArrayList<Customer> customerArrayList = new ArrayList<>();
    DBClient client;
    private Company selectedCompany;
    private Customer selectedCostumer;
    boolean running = true;
    private String inputString;
    CarDao carDao;
    CompanyDao companyDao;
    CustomerDao customerDao;
    public void setSelectedCar(Car car) {
       this.selectedCostumer.setCar(car);
    }
    public void setSelectedCustomer(Customer selectedCostumer) {
        this.selectedCostumer = selectedCostumer;
    }
    public Customer getSelectedCostumer() {
        return selectedCostumer;
    }
    public void setSelectedCompany(Company selectedCompany) {
        this.selectedCompany = selectedCompany;
    }
    public Company getSelectedCompany() {
        return selectedCompany;
    }
    public void transferData(CarSharingApp otherObject) {
        this.companyArrayList = otherObject.companyArrayList;
        this.carArrayList = otherObject.carArrayList;
        this.customerArrayList = otherObject.customerArrayList;
        this.selectedCompany = otherObject.getSelectedCompany();
        this.selectedCostumer = otherObject.selectedCostumer;
        this.carDao = otherObject.carDao;
        this.customerDao = otherObject.customerDao;
        this.companyDao = otherObject.companyDao;
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