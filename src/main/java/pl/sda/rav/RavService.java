package pl.sda.rav;

import pl.sda.rav.dao.orders.OrdersDao;
import pl.sda.rav.dao.users.UsersDao;
import pl.sda.rav.dao.vehicles.SearchParameters;
import pl.sda.rav.dao.vehicles.VehiclesDao;
import pl.sda.rav.domain.Period;
import pl.sda.rav.domain.users.User;
import pl.sda.rav.domain.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RavService {
    private User user;

    private UsersDao usersDao;
    private VehiclesDao vehiclesDao;
    private OrdersDao ordersDao;

    public RavService(UsersDao usersDao, VehiclesDao vehiclesDao, OrdersDao ordersDao) {
        this.usersDao = usersDao;
        this.vehiclesDao = vehiclesDao;
        this.ordersDao = ordersDao;
    }

    public boolean login(String login, String password) {
        Optional<User > user = usersDao.find(login, password);
        if(!user.isPresent()) {
            return false;
        }

        this.user = user.get();
        return true;
    }

    public List<Vehicle> searchVehicles(SearchParameters searchParameters) {
        if(user == null) {
            throw new IllegalStateException("User must be login to search vehicles");
        }
        return new ArrayList<>(vehiclesDao.searchVehicles(searchParameters));
    }

    public boolean orderVehicle(Vehicle vehicle, Period period) {
        if(user == null) {
            throw new IllegalStateException("User must be login to search vehicles");
        }

        boolean available = ordersDao.isAvailable(vehicle.getVin(), period);
        if(!available) {
            return false;
        }

        ordersDao.orderVehicle(user, vehicle, period);
        return true;
    }
}