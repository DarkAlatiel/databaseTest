import java.sql.*;
import java.util.Properties;

public class DatabaseService {

    private static final String connectionString =
            "jdbc:mysql://localhost:3306/dbtest";

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "mysql");
        properties.put("useSSL", "true");
        properties.put("serverTimezone", "Europe/Moscow");
        return properties;
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    connectionString, getProperties());
            String status = connection.isClosed()
                    ? "закрыто" : "открыто";
            System.out.printf("Подключение %s\n", status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createUser(User user) {
        String query = "INSERT INTO users " +
                "(name, birthdate, login, password, gender) " +
                "VALUES (?,?,?,?,?)";
        Connection connection = getConnection();
        int count = 0;
        try {
            PreparedStatement statement =
                    connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setDate(2, user.getBirthdate());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.getGender());
            count = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("Пользователь%sдобавлен",
                    count > 0 ? " " : " не ");
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
