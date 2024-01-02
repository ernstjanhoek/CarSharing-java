package carsharing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Company extends CarSharingObject {
    Company(String name) {
        super(name);
    }
}
class CompanyDao  extends CarSharingDao<Company> {
    CompanyDao(DBClient client) {
        super(client);
    }
    @Override
    public ArrayList<Company> findAll() throws SQLException {
        ArrayList<Company> companyArray = new ArrayList<>();
        Statement statement = client.connection.createStatement();
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
    @Override
    public Company findById(int id) {
        return null;
    }
    @Override
    public void add(Company object) throws SQLException {
        String insertCompany = "INSERT INTO company (name) VALUES (?)";
        PreparedStatement statement = client.connection.prepareStatement(insertCompany);
        statement.setString(1, object.getName());
        statement.execute();
    }
    @Override
    public void update(Company object) {
    }

    @Override
    public void delete(int id) throws SQLException {
    }
}