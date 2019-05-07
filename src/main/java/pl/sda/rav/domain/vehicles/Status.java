package pl.sda.rav.domain.vehicles;

public enum Status {
    AVAILABLE(true),
    IN_REPAIR(false),
    RENT(false);

    private boolean isAvailable;

    Status(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
