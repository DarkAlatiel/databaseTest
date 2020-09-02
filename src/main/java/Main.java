import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<User> users = DatabaseService.selectUsers();
        for (User user : users) {
            String birthdate = user.getBirthdate() != null
                    ? formatter.format(user.getBirthdate()) : "\t\t";
            System.out.printf(
                    "%d\t%s\t%s\t%s\t%s\t%s\n",
                    user.getId(),
                    user.getName(),
                    birthdate,
                    user.getLogin(),
                    user.getPassword(),
                    user.getGender() ? "мужской" : "женский"
            );
        }
        /*
        User newUser = new User(0, "Иван", null, "ivan", "123", true);
        int count = DatabaseService.createUser(newUser);
        System.out.printf("Пользователь%sдобавлен", count > 0 ? " " : " не ");
        */
        /*
                User editUser = users.get(1);
        editUser.setPassword("J3QQ4");
        int count = DatabaseService.editUser(editUser);
        System.out.printf("Пользователь%sизменён\n", count > 0 ? " " : " не ");
        */
        /*
        User oldUser = new User(0, "Степан",
            Date.valueOf("2006-07-08"), "stepan",
                "123", true);
        int count = DatabaseService.deleteUser(oldUser);
        System.out.printf("Пользователь%sудалён\n", count > 0 ? " " : " не ");
         */
    }
}
