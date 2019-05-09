package pl.sda.rav.domain.vehicles;

import java.time.LocalDate;

public class Motorboat extends Vehicle implements SwimmingVehicle {
    private int maxDistanceMiles;
    private int displacement;

    public Motorboat(String vin, String name, LocalDate productionDate, int maxDistanceMiles, int displacement) {
        super(vin, name, productionDate);
        this.maxDistanceMiles = maxDistanceMiles;
        this.displacement = displacement;
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
    public String toString() {
        return "Motorboat{" +
                "maxDistanceMiles=" + maxDistanceMiles +
                ", displacement=" + displacement +
                ", vin='" + getVin() + '\'' +
                ", name='" + getName() + '\'' +
                ", productionDate=" + getProductionDate() +
                '}';
    }
}
