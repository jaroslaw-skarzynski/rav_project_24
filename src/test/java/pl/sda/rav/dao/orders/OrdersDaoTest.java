package pl.sda.rav.dao.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rav.UsersSamples;
import pl.sda.rav.VehiclesSamples;
import pl.sda.rav.domain.Period;
import pl.sda.rav.domain.orders.Order;
import pl.sda.rav.domain.users.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OrdersDaoTest {
    private OrdersDao ordersDao;

    @BeforeEach
    void setUp() {
        ordersDao = new OrdersDao();
        ordersDao.orderVehicle(UsersSamples.ADAM, VehiclesSamples.FORD_KUGA, new Period(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1)));
        ordersDao.orderVehicle(UsersSamples.MIETEK, VehiclesSamples.SUN_FLOWER, new Period(LocalDate.of(2019, 7, 1), LocalDate.of(2019, 7, 31)));
        ordersDao.orderVehicle(UsersSamples.ADAM, VehiclesSamples.FORD_KUGA, new Period(LocalDate.of(2019, 2, 15), LocalDate.of(2019, 2, 19)));
        ordersDao.orderVehicle(UsersSamples.ADAM, VehiclesSamples.FORD_KUGA, new Period(LocalDate.of(2019, 3, 1), LocalDate.of(2019, 3, 20)));
        ordersDao.orderVehicle(UsersSamples.MIETEK, VehiclesSamples.AMPHIBIAN, new Period(LocalDate.of(2019, 2, 10), LocalDate.of(2019, 2, 25)));
        ordersDao.orderVehicle(UsersSamples.EWA, VehiclesSamples.SUN_FLOWER, new Period(LocalDate.of(2019, 8, 1), LocalDate.of(2019, 8, 31)));
    }

    @Test
    public void shouldNotAcceptDuplicatedOrders() {
        // given
        OrdersDao ordersDao = new OrdersDao();

        // when
        ordersDao.orderVehicle(UsersSamples.ADAM, VehiclesSamples.FORD_KUGA, new Period(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1)));
        ordersDao.orderVehicle(UsersSamples.ADAM, VehiclesSamples.FORD_KUGA, new Period(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1)));

        // then
        Set<Order> orders = ordersDao.getOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void shouldNotAcceptOrderOfVehicleThatIsNotAvailableInPeriod() {
        // given
        OrdersDao ordersDao = new OrdersDao();

        // when
        boolean successOrder = ordersDao.orderVehicle(UsersSamples.ADAM, VehiclesSamples.FORD_KUGA, new Period(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1)));
        boolean unSuccessOrder = ordersDao.orderVehicle(UsersSamples.ADAM, VehiclesSamples.FORD_KUGA, new Period(LocalDate.of(2019, 1, 22), LocalDate.of(2019, 2, 10)));

        // then
        assertTrue(successOrder);
        assertFalse(unSuccessOrder);

        Set<Order> orders = ordersDao.getOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void shouldCheckVehicleAviability() {
        // given
        // when
        boolean shouldBeAvailable = ordersDao.isAvailable(VehiclesSamples.FORD_KUGA.getVin(), new Period(LocalDate.of(2019, 2, 2), LocalDate.of(2019, 2, 14)));
        boolean shouldBeUnAvailable = ordersDao.isAvailable(VehiclesSamples.AMPHIBIAN.getVin(), new Period(LocalDate.of(2019, 2, 2), LocalDate.of(2019, 2, 14)));

        // then
        assertTrue(shouldBeAvailable);
        assertFalse(shouldBeUnAvailable);
    }

    @Test
    public void shouldCheckVehicleAviabilityForOverlapingPeriod() {
        // given
        // when
        boolean shouldBeUnAvailable = ordersDao.isAvailable(VehiclesSamples.FORD_KUGA.getVin(), new Period(LocalDate.of(2019, 2, 25), LocalDate.of(2019, 3, 10)));

        // then
        assertFalse(shouldBeUnAvailable);
    }

    @Test
    public void shouldReturnEmptyListForUserWithoutOrders() {
        // given
        User user = UsersSamples.ZOFIA;

        // when
        List<Order> orderedByUser = ordersDao.getOrdersByUser(user);

        // then
        assertEquals(0, orderedByUser.size());
    }

    @Test
    public void shouldReturnListOfOrders() {
        // given
        User user = UsersSamples.MIETEK;

        // when
        List<Order> orderedByUser = ordersDao.getOrdersByUser(user);

        // then
        assertEquals(2, orderedByUser.size());
        assertEquals(VehiclesSamples.AMPHIBIAN, orderedByUser.get(0).getVehicle());
        assertEquals(VehiclesSamples.SUN_FLOWER, orderedByUser.get(1).getVehicle());
    }

    @Test
    public void shouldReturnUsersOrdersMap() {
        // given
        // when
        Map<User, List<Order>> userOrderMap = ordersDao.getUserOrderMap();

        // then
        assertEquals(3, userOrderMap.size());
        assertEquals(2, userOrderMap.get(UsersSamples.MIETEK).size());
        assertEquals(3, userOrderMap.get(UsersSamples.ADAM).size());
        assertEquals(1, userOrderMap.get(UsersSamples.EWA).size());
    }
}