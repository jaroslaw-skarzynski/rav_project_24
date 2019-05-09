package pl.sda.rav.dao.vehicles;

import pl.sda.rav.domain.Period;

public class SearchParameters {
    private VehicleCategory category;
    private VehicleAge age;
    private Period periodToCheck;

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public boolean hasCategory() {
        return category != null;
    }

    public void setAge(VehicleAge age) {
        this.age = age;
    }

    public VehicleAge getAge() {
        return age;
    }

    public boolean hasAge() {
        return age != null;
    }

    public void setPeriodToCheck(Period periodToCheck) {
        this.periodToCheck = periodToCheck;
    }

    public Period getPeriodToCheck() {
        return periodToCheck;
    }

    public boolean hasPeriodToCheck() {
        return periodToCheck != null;
    }
}
