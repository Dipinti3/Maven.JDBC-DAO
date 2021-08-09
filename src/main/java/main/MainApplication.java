package main;

import daos.CarRepo;
import main.ConnectionFactory;
import models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class MainApplication {
    public static void main(String[] args) {
        ConnectionFactory.registerJDBCDriver();
        Connection mysqlDbConnection = ConnectionFactory.getConnection("mysql");
        CarRepo carRepo = new CarRepo(mysqlDbConnection);
        executeStatement(mysqlDbConnection, "DROP DATABASE IF EXISTS car;");
        executeStatement(mysqlDbConnection, "CREATE DATABASE IF NOT EXISTS car;");
        executeStatement(mysqlDbConnection, "USE car;");
        executeStatement(mysqlDbConnection, new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS carTable(")
                .append("id int auto_increment primary key,")
                .append("make varchar(255) not null,")
                .append("model varchar(255) not null,")
                .append("year int not null,")
                .append("color varchar(255),")
                .append("vin varchar(255));")
                .toString());
        carRepo.create(new Car(1, "Ford", "Edge", 2005, "Blue", "YHTFG456"));
        carRepo.create(new Car(2, "Tata","Jaguar", 1999, "Metallic Green", "STFD354"));
        carRepo.create(new Car( 3,"Honda","Civic", 2001, "Blood Red", "THGF874"));
        System.out.println(carRepo.findAll());
    }

    static ResultSet executeQuery(Connection connection, String sqlQuery) {
        try {
            Statement statement = getScrollableStatement(connection);
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void printResults(ResultSet resultSet) {
        try {
            for (int rowNumber = 0; resultSet.next(); rowNumber++) {
                String firstColumnData = resultSet.getString(1);
                String secondColumnData = resultSet.getString(2);
                String thirdColumnData = resultSet.getString(3);
                System.out.println(new StringJoiner("\n")
                        .add("Row number = " + rowNumber)
                        .add("First Column = " + firstColumnData)
                        .add("Second Column = " + secondColumnData)
                        .add("Third column = " + thirdColumnData));
            }
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void executeStatement(Connection connection, String sqlStatement) {
        try {
            Statement statement = getScrollableStatement(connection);
            statement.execute(sqlStatement);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static Statement getScrollableStatement(Connection connection) {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try { // scrollable statements can be iterated more than once without closing
            return connection.createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}
