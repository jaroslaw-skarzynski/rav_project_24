package pl.sda.rav.domain.orders;

import pl.sda.rav.domain.Period;
import pl.sda.rav.domain.users.User;
import pl.sda.rav.domain.vehicles.Vehicle;

public class Order {
    private static int COUNT = 0;

    private int id;
    private User customer;
    private Vehicle vehicle;
    private Period period;

    public Order(User customer, Vehicle vehicle, Period period) {
        this.id = COUNT++;
        this.customer = customer;
        this.vehicle = vehicle;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Period getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                ", period=" + period +
                '}';
    }
}