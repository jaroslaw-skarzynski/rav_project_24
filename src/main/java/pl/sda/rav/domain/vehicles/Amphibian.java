package pl.sda.rav.domain.vehicles;

import java.time.LocalDate;

public class Amphibian extends Vehicle {
    private int maxDistanceKm;
    private int maxDistanceMiles;
    private int displacement;

    public Amphibian(String vin, String status, String name, LocalDate productionDate, int maxDistanceKm, int maxDistanceMiles, int displacement) {
        super(vin, status, name, productionDate);
        this.maxDistanceKm = maxDistanceKm;
        this.maxDistanceMiles = maxDistanceMiles;
        this.displacement = displacement;
    }

    public int getMaxDistanceKm() {
        return maxDistanceKm;
    }

    public int getMaxDistanceMiles() {
        return maxDistanceMiles;
    }

    public int getDisplacement() {
        return displacement;
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
