package pl.sda.rav.dao.vehicles;

public class SearchParametersForSwimming extends SearchParameters {
    private Integer displacementFrom;
    private Integer displacementTo;

    public SearchParametersForSwimming() {
        setCategory(VehicleCategory.SWIMMING);
    }

    public void setDisplacementFrom(int displacementFrom) {
        this.displacementFrom = displacementFrom;
    }

    public Integer getDisplacementFrom() {
        return displacementFrom;
    }

    public boolean hasDisplacementFrom() {
        return displacementFrom != null;
    }

    public void setDisplacementTo(int displacementTo) {
        this.displacementTo = displacementTo;
    }

    public Integer getDisplacementTo() {
        return displacementTo;
    }

    public boolean hasDisplacementTo() {
        return displacementTo != null;
    }
}
