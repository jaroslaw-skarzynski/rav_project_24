package pl.sda.rav.dao.orders;

import pl.sda.rav.domain.Period;
import pl.sda.rav.domain.orders.Order;
import pl.sda.rav.domain.users.User;
import pl.sda.rav.domain.vehicles.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class OrdersDao {
    private Set<Order> orders = new LinkedHashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }

    public boolean isAvailable(String vin, Period periodToCheck) {
        for (Order order : orders) {
            Vehicle vehicle = order.getVehicle();
            if (!vehicle.getVin().equals(vin)) {
                continue;
            }

            Period period = order.getPeriod();
            if (!(periodToCheck.getEndDate().isBefore(period.getStartDate()) ||
                    periodToCheck.getStartDate().isAfter(period.getEndDate()))) {
                return false;
            }
        }

        return true;
    }

    public boolean orderVehicle(User user, Vehicle vehicle, Period period) {
        Order order = new Order(user, vehicle, period);
        orders.add(order);
        return true;
    }

    public List<Order> getOrdersByUser(User user) {
        return orders.stream()
                .filter(order -> order.getCustomer().getLogin().equals(user.getLogin()))
                .sorted(Comparator.comparing(order -> order.getPeriod().getStartDate()))
                .collect(Collectors.toList());
    }

    public Map<User, List<Order>> getUserOrderMap() {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
    }
}