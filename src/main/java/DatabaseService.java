import java.sql.*;
import java.util.ArrayList;
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

    public static ArrayList<User> selectUsers() {
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * FROM users";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Date birthdate = resultSet.getDate("birthdate");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Boolean gender = resultSet.getBoolean("gender");
                User user = new User(id, name, birthdate, login, password, gender);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                return users;
            }

        }

    }

    public static int createUser(User user) {
        String query = "INSERT INTO users " +
                "(name, birthdate, login, password, gender) " +
                "VALUES (?,?,?,?,?)";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getBirthdate());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.getGender());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int editUser(User user) {
        String query = "UPDATE users SET " +
                "name = ?, birthdate = ?, login = ?, " +
                "password = ?, gender = ? WHERE id = ?";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getBirthdate());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, user.getGender());
            preparedStatement.setLong(6, user.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int deleteUser(User user) {
        String query = "DELETE FROM users " +
                "WHERE id = ?";
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
