package pl.sda.rav.domain.vehicles;

import java.time.LocalDate;

public abstract class Vehicle implements Comparable<Vehicle> {
    private String vin;
    private String name;
    private LocalDate productionDate;

    public Vehicle(String vin, String name, LocalDate productionDate) {
        this.vin = vin;
        this.name = name;
        this.productionDate = productionDate;
    }

    public String getVin() {
        return vin;
    }

    public String getName() {
        return name;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    @Override
    public int compareTo(Vehicle o) {
        if(this.productionDate.compareTo(o.getProductionDate()) != 0) {
            return this.productionDate.compareTo(o.getProductionDate());
        }

        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        return vin != null ? vin.equals(vehicle.vin) : vehicle.vin == null;

    }

    @Override
    public int hashCode() {
        return vin != null ? vin.hashCode() : 0;
    }
}