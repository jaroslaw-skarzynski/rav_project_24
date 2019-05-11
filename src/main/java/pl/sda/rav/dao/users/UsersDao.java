package pl.sda.rav.dao.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sda.rav.domain.users.User;

import java.util.*;

public class UsersDao {
    private final Logger logger = LoggerFactory.getLogger(UsersDao.class);

    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {

        return new TreeSet<>(users);
    }

    public boolean add(User user) {
//        if (users.contains(user)) {
//            logger.error("User already exists in DAO, user: {}", user);
//            return false;
//        }
        for (User user1 : users) {
            if(user1.getLogin().equals(user.getLogin())){
                logger.error("User already exists in DAO, user: {}", user);
                return false;
            }
        }

        users.add(user);
        return true;
    }

    public boolean remove(String login) {
        List<User> usersList = new ArrayList<>(users);
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            if (user.getLogin().equals(login)) {
                users.remove(user);
                return true;
            }
        }

        logger.warn("There is no user (to remove) with login: {}", login);
        return false;
    }

    public Optional<User> find(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }
}