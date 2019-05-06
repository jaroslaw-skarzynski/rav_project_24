package pl.sda.rav.domain.vehicles;

import java.time.LocalDate;

public abstract class Vehicle {
    private String vin;
    private String status;
    private String name;
    private LocalDate productionDate;

    public Vehicle(String vin, String status, String name, LocalDate productionDate) {
        this.vin = vin;
        this.status = status;
        this.name = name;
        this.productionDate = productionDate;
    }

    public String getVin() {
        return vin;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }
}