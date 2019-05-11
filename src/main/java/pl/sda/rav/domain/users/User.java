package pl.sda.rav.domain.users;

import java.util.Comparator;

public class User implements Comparable<User> {
    private String login;
    private String password;
    private UserType userType;

    public User(String login, String password, UserType userType) {
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public int compareTo(User o) {
        Comparator<User> userComparator = Comparator.comparing(User::getLogin).reversed();
        return userComparator.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login != null ? login.equals(user.login) : user.login == null;

    }

    @Override
    public int hashCode() {
        return password != null ? password.hashCode() : 0;
    }
}
