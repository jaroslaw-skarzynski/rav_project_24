package pl.sda.rav.dao;

public class SearchParameters {
    private VehicleCategory category;
    private VehicleStatus status;
    private VehicleAge age;

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public boolean hasCategory() {
        return category != null;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public boolean hasStatus() {
        return status != null;
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
}
