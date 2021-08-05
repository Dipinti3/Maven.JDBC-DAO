import java.sql.*;

public class ConnectionFactory {
    static void registerJDBCDriver(){
        try{
            DriverManager.registerDriver(Driver.class.newInstance());
        }
        catch (InstantiationException | IllegalAccessException | SQLException e1) {
            throw new Error();
        }
    }

    static Statement getScrollableStatement(Connection connection) {
        int resultSetType =  ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try { // scrollable statements can be iterated more than once without closing
            return connection.createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    static void executeStatement(Connection connection, String sqlStatement) {
        try {
            Statement statement = getScrollableStatement(connection);
            statement.execute(sqlStatement);
            connection.commit();
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}
