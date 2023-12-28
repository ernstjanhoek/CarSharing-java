import java.util.ArrayList;
public interface CarSharingDao<T> {
    ArrayList<T> findAll();
    T findById(int id);
    void add(T object);
    void update(T object);
}