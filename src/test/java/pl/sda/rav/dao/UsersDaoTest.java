package pl.sda.rav.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rav.domain.users.User;
import pl.sda.rav.domain.users.UserType;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsersDaoTest {
    private UsersDao usersDao;
    private User mietek = new User("mietek", "tajne_haslo", UserType.CUSTOMER);
    private User adam = new User("adam", "moje_haslo-", UserType.ADMIN);
    private User ewa = new User("ewa", "th", UserType.ADMIN);
    private User zofia = new User("zofia", "sekretne_hasło", UserType.CUSTOMER);

    @BeforeEach
    void setUp() {
        usersDao = new UsersDao();
        usersDao.add(mietek);
        usersDao.add(zofia);
        usersDao.add(ewa);
        usersDao.add(adam);
    }

    @Test
    public void shouldGetAllUsers() {
        // given
        // when
        Set<User> users = usersDao.getUsers();

        // then
        assertIterableEquals(Arrays.asList(zofia, mietek, ewa, adam), users);
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
        User newUser = new User(adam.getLogin(), "has_6", UserType.CUSTOMER);

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
        boolean removed = usersDao.remove(zofia.getLogin());
        assertTrue(removed);
        removed = usersDao.remove(ewa.getLogin());
        assertTrue(removed);

        Set<User> users = usersDao.getUsers();

        // then
        assertEquals(2, users.size());
        assertFalse(users.contains(ewa));
        assertFalse(users.contains(zofia));
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