import java.sql.Date;

public class User {

    public User(long id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }

    public User(long id, String name, Date birthdate, String login, String password, boolean gender) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.gender = gender;
    }

    private long id;
    private String name;
    private Date birthdate;
    private String login;
    private String password;
    private boolean gender;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
