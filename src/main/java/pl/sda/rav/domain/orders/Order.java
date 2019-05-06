package pl.sda.rav.domain.orders;

import pl.sda.rav.domain.users.User;
import pl.sda.rav.domain.vehicles.Vehicle;

import java.time.LocalDateTime;

public class Order {
    private static int COUNT = 0;

    private int id;
    private User customer;
    private Vehicle vehicle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Order(User customer, Vehicle vehicle, LocalDateTime startDate) {
        this.id = COUNT++;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}