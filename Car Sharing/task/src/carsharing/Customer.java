package carsharing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

class Customer extends CarSharingObject {
    Customer(String name) {
        super(name);
    }
    private Car car;
    public void setCar(Car car) {
        this.car = car;
    }
    public Car getCar() {
        return this.car;
    }
}
class CustomerDao extends CarSharingDao<Customer> {
    CustomerDao(DBClient client) {
        super(client);
    }
    @Override
    public ArrayList<Customer> findAll() throws SQLException {
        ArrayList<Customer> customerArray = new ArrayList<>();
        Statement statement = super.client.connection.createStatement();
        String selectAll = "SELECT * FROM customer;";
        ResultSet resultSetItem = statement.executeQuery(selectAll);

        while (resultSetItem.next()) {
            int id = resultSetItem.getInt("id");
            String name = resultSetItem.getString("name");
            Customer customer = new Customer(name);
            customer.setId(id);
            customerArray.add(customer);
        }
        return customerArray;
    }
    @Override
    public Customer findById(int id) {
        return null;
    }
    @Override
    public void add(Customer object) throws SQLException {
        String insertCustomer = "INSERT INTO customer (name) VALUES (?)";
        PreparedStatement statement = client.connection.prepareStatement(insertCustomer);
        statement.setString(1, object.getName());
        statement.execute();
    }
    @Override
    public void update(Customer object) {
    }

    @Override
    public void delete(int id) throws SQLException {
    }

    public void rentCar(Customer object) throws SQLException {
        String updateRentedCar = "BEGIN TRANSACTION; UPDATE customer SET rented_car_id = ? WHERE id = ?; UPDATE car SET available = FALSE WHERE id = ?; COMMIT;";
        PreparedStatement statement = client.connection.prepareStatement(updateRentedCar);
        statement.setInt(1, object.getCar().getId());
        statement.setInt(2, object.getId());
        statement.setInt(3, object.getCar().getId());
        statement.execute();
    }
    public void returnCar(Customer object) throws SQLException {
        String returnCar = "BEGIN TRANSACTION; UPDATE car SET available = TRUE WHERE id = ?; UPDATE customer SET rented_car_id = null WHERE id = ?; COMMIT;";
        PreparedStatement statement = client.connection.prepareStatement(returnCar);
        statement.setInt(1, object.getCar().getId());
        statement.setInt(2, object.getId());
        statement.execute();
    }
}