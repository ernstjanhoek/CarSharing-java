package carsharing;

import java.sql.SQLException;
import java.util.ArrayList;
abstract class CarSharingDao<T> {
    CarSharingDao(DBClient client) {
        this.client = client;
    }
    DBClient client;
    abstract ArrayList<T> findAll() throws SQLException;
    abstract T findById(int id);
    abstract void add(T object) throws SQLException;
    abstract void update(T object) throws SQLException;
    abstract void delete(int id) throws SQLException;
}