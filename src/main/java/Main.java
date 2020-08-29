import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        User user = new User(0, "Иван", null, "ivan", "123", true);
        DatabaseService databaseService = new DatabaseService();
        databaseService.createUser(user);
    }
}
