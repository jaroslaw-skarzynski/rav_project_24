package pl.sda.rav.dao.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rav.UsersSamples;
import pl.sda.rav.domain.users.User;
import pl.sda.rav.domain.users.UserType;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDaoTest {
    private UsersDao usersDao;

    @BeforeEach
    void setUp() {
        usersDao = new UsersDao();
        usersDao.add(UsersSamples.MIETEK);
        usersDao.add(UsersSamples.ZOFIA);
        usersDao.add(UsersSamples.EWA);
        usersDao.add(UsersSamples.ADAM);
    }

    @Test
    public void shouldGetAllUsers() {
        // given
        // when
        Set<User> users = usersDao.getUsers();

        // then
        assertIterableEquals(Arrays.asList(UsersSamples.ZOFIA, UsersSamples.MIETEK, UsersSamples.EWA, UsersSamples.ADAM), users);
    }

    @Test
    public void shouldAddAnotherUser() {
        // given
        User newUser = new User("gosia", "has_6", UserType.CUSTOMER);

        // when
        boolean added = usersDao.add(newUser);
        Set<User> users = usersDao.getUsers();

        // then
        assertTrue(added);
        assertEquals(5, users.size());
        assertTrue(users.contains(newUser));
    }

    @Test
    public void shouldNotAddDuplicateUser() {
        // given
        User newUser = new User(UsersSamples.ADAM.getLogin(), "has_6", UserType.CUSTOMER);

        // when
        boolean added = usersDao.add(newUser);
        Set<User> users = usersDao.getUsers();

        // then
        assertFalse(added);
        assertEquals(4, users.size());
    }

    @Test
    public void shouldRemoveExistingUser() {
        // given
        // when
        boolean removed = usersDao.remove(UsersSamples.ZOFIA.getLogin());
        assertTrue(removed);
        removed = usersDao.remove(UsersSamples.EWA.getLogin());
        assertTrue(removed);

        Set<User> users = usersDao.getUsers();

        // then
        assertEquals(2, users.size());
        assertFalse(users.contains(UsersSamples.EWA));
        assertFalse(users.contains(UsersSamples.ZOFIA));
    }

    @Test
    public void shouldNotRemoveNotExistingUser() {
        // given
        // when
        boolean removed = usersDao.remove("XYZ");
        Set<User> users = usersDao.getUsers();

        // then
        assertFalse(removed);
        assertEquals(4, users.size());
    }
}