package pl.sda.rav.domain.vehicles;

import java.time.LocalDate;

public class Car extends Vehicle implements DrivingVehicle {
    private int maxDistance;
    private BodyType bodyType;

    public Car(String vin, Status status, String name, LocalDate productionDate, int maxDistance, BodyType bodyType) {
        super(vin, status, name, productionDate);
        this.maxDistance = maxDistance;
        this.bodyType = bodyType;
    }

    @Override
    public int getMaxDistanceKm() {
        return maxDistance;
    }

    @Override
    public BodyType getBodyType() {
        return bodyType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "maxDistance=" + maxDistance +
                ", bodyType=" + bodyType +
                ", vin='" + getVin() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", name='" + getName() + '\'' +
                ", productionDate=" + getProductionDate() +
                '}';
    }
}
