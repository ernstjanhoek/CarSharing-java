package carsharing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Car extends CarSharingObject {
    Car(String name) {
        super(name);
    }
    private Integer companyId;
    private boolean available;
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setAvailable(boolean state) {
        this.available = state;
    }
    public boolean isAvailable() {
        return available;
    }
}
class CarDao extends CarSharingDao<Car> {
    CarDao(DBClient client) {
        super(client);
    }

    @Override
    ArrayList<Car> findAll() throws SQLException {
        return null;
    }

    ArrayList<Car> findAllByCompanyID(Company object) throws SQLException {
        ArrayList<Car> carArray = new ArrayList<>();
        String selectAll = "SELECT * FROM car WHERE company_id = ?;";
        PreparedStatement statement = super.client.connection.prepareStatement(selectAll);
        statement.setInt(1, object.getId());
        ResultSet resultSetItem = statement.executeQuery();

        while (resultSetItem.next()) {
            int id = resultSetItem.getInt("id");
            String name = resultSetItem.getString("name");
            boolean available = resultSetItem.getBoolean("available");
            Car car = new Car(name);
            car.setId(id);
            car.setAvailable(available);
            carArray.add(car);
        }
        return carArray;
    }
    ArrayList<Car> findAllAvailable() throws SQLException {
        ArrayList<Car> carArray = new ArrayList<>();
        Statement statement = super.client.connection.createStatement();
        String selectAll = "SELECT * FROM car where available = TRUE;";
        ResultSet resultSetItem = statement.executeQuery(selectAll);

        while (resultSetItem.next()) {
            int id = resultSetItem.getInt("id");
            String name = resultSetItem.getString("name");
            boolean available = resultSetItem.getBoolean("available");
            Car car = new Car(name);
            car.setId(id);
            car.setAvailable(available);
            carArray.add(car);
        }
        return carArray;
    }
    @Override
    public Car findById(int id) {
        return null;
    }
    @Override
    public void add(Car object) throws SQLException {
        String insertCar = "INSERT INTO car (name, company_id, available) VALUES (? , ? , ?)";
        PreparedStatement statement = client.connection.prepareStatement(insertCar);
        statement.setString(1, object.getName());
        statement.setInt(2, object.getCompanyId());
        statement.setBoolean(3, true);
        statement.execute();
    }
    @Override
    public void update(Car object) throws SQLException {
    }
    @Override
    public void delete(int id) throws SQLException {
    }
}