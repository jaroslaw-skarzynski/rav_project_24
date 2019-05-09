package pl.sda.rav;

import org.junit.jupiter.api.Test;
import pl.sda.rav.dao.orders.OrdersDao;
import pl.sda.rav.dao.users.UsersDao;
import pl.sda.rav.dao.vehicles.*;
import pl.sda.rav.domain.Period;
import pl.sda.rav.domain.orders.Order;
import pl.sda.rav.domain.vehicles.BodyType;
import pl.sda.rav.domain.vehicles.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RavServiceIntegrationTest {

    @Test
    void shouldRentACar() {
        //given
        UsersDao usersDao = new UsersDao();
        usersDao.add(UsersSamples.EWA);
        usersDao.add(UsersSamples.MIETEK);
        usersDao.add(UsersSamples.ADAM);
        usersDao.add(UsersSamples.ZOFIA);

        OrdersDao ordersDao = new OrdersDao();

        VehiclesDao vehiclesDao = new VehiclesDao(ordersDao);
        vehiclesDao.add(VehiclesSamples.FORD_KUGA);
        vehiclesDao.add(VehiclesSamples.AMPHIBIAN);
        vehiclesDao.add(VehiclesSamples.NIGHT_CRUISER);
        vehiclesDao.add(VehiclesSamples.SUN_FLOWER);
        vehiclesDao.add(VehiclesSamples.TOYOTA_YARIS);

        //when
        RavService ravService = new RavService(usersDao, vehiclesDao, ordersDao);

        ravService.login(UsersSamples.MIETEK.getLogin(), UsersSamples.MIETEK.getPassword());

        SearchParametersForDriving searchParameters = new SearchParametersForDriving();
        searchParameters.setAge(VehicleAge.YOUNG);
        searchParameters.setBodyType(BodyType.HATCHBACK);
        List<Vehicle> vehicles = ravService.searchVehicles(searchParameters);

        assertEquals(1, vehicles.size());
        assertEquals(VehiclesSamples.TOYOTA_YARIS, vehicles.get(0));

        boolean success = ravService.orderVehicle(vehicles.get(0), new Period(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 3, 1)));

        //then
        assertTrue(success);
        Set<Order> orders = ordersDao.getOrders();
        assertEquals(1, orders.size());
    }
}