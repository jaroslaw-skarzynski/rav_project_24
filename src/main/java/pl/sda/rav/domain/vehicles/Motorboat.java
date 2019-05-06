package pl.sda.rav.domain.vehicles;

import java.time.LocalDate;

public class Motorboat extends Vehicle{
    private int maxDistance;
    private int displacement;

    public Motorboat(String vin, Status status, String name, LocalDate productionDate, int maxDistance, int displacement) {
        super(vin, status, name, productionDate);
        this.maxDistance = maxDistance;
        this.displacement = displacement;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getDisplacement() {
        return displacement;
    }

    @Override
    public String toString() {
        return "Motorboat{" +
                "maxDistance=" + maxDistance +
                ", displacement=" + displacement +
                ", vin='" + getVin() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", name='" + getName() + '\'' +
                ", productionDate=" + getProductionDate() +
                '}';
    }
}
