package daos;

import models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepo implements ConnectionInterface{
    private Connection connection;
    public CarRepo(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Connection getConnection() {
        return connection;
    }

    public void create(Car car) {
        executeStatement(String.format(new StringBuilder()
                        .append("INSERT INTO car.carTable(")
                        .append("id, make, model, year, color,vin)")
                        .append("VALUES (%s, '%s','%s', %s, '%s', '%s');")
                        .toString(),
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getColor(),
                car.getVin()));
    }

    public List<Car> findAll() {
        ResultSet resultSet = executeQuery("SELECT * FROM car.carTable;");
        List<Car> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String make = resultSet.getString(2);
                String model = resultSet.getString(3);
                String year = resultSet.getString(4);
                String color = resultSet.getString(5);
                String vin=resultSet.getString(6);
                list.add(new Car(
                        Integer.parseInt(id),
                        make,
                        model,
                        Integer.parseInt(year),
                        color,
                        vin));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Car findById(int id) {
        return findAll()
                .stream()
                .filter(car -> car.getId()==(id))
                .findAny()
                .get();
    }

    public void update(int id, Car newData) {
        executeStatement(String.format(new StringBuilder()
                        .append("UPDATE car.carTable ")
                        .append("SET make = '%s', " +
                                "model = '%s', year=%s,color = '%s', vin = '%s' WHERE id = %s;")
                        .toString(),
                newData.getMake(),
                newData.getModel(),
                newData.getYear(),
                newData.getColor(),
                newData.getVin(),
                id));
    }

    public void delete(int id) {
        Car car = findById(id);
        executeStatement(String.format(new StringBuilder()
                        .append("DELETE FROM car.carTable WHERE id = %s")
                        .toString(),
                id));
        System.out.printf("%s has been deleted", car.toString());
    }

    public void delete(Car car) {
        int id = car.getId();
        delete(id);
    }

}
