package pl.sda.rav;

import pl.sda.rav.domain.users.User;
import pl.sda.rav.domain.users.UserType;

public class UsersSamples {
    public static final User MIETEK = new User("mietek", "tajne_haslo", UserType.CUSTOMER);
    public static final User ADAM = new User("adam", "moje_haslo-", UserType.ADMIN);
    public static final User EWA = new User("ewa", "th", UserType.ADMIN);
    public static final User ZOFIA = new User("zofia", "sekretne_hasło", UserType.CUSTOMER);
}
