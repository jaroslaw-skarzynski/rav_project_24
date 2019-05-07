package pl.sda.rav.domain.vehicles;

import java.time.LocalDate;

public class Amphibian extends Vehicle implements SwimmingVehicle, DrivingVehicle {
    private int maxDistanceKm;
    private int maxDistanceMiles;
    private int displacement;

    public Amphibian(String vin, Status status, String name, LocalDate productionDate, int maxDistanceKm, int maxDistanceMiles, int displacement) {
        super(vin, status, name, productionDate);
        this.maxDistanceKm = maxDistanceKm;
        this.maxDistanceMiles = maxDistanceMiles;
        this.displacement = displacement;
    }

    @Override
    public int getMaxDistanceKm() {
        return maxDistanceKm;
    }

    @Override
    public int getMaxDistanceMiles() {
        return maxDistanceMiles;
    }

    @Override
    public int getDisplacement() {
        return displacement;
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.AMPHIBIAN;
    }

    @Override
    public String toString() {
        return "Amphibian{" +
                "maxDistanceKm=" + maxDistanceKm +
                ", maxDistanceMiles=" + maxDistanceMiles +
                ", displacement=" + displacement +
                ", vin='" + getVin() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", name='" + getName() + '\'' +
                ", productionDate=" + getProductionDate() +
                '}';
    }
}
