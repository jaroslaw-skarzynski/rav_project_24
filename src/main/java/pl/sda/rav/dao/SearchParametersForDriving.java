package pl.sda.rav.dao;

import pl.sda.rav.domain.vehicles.BodyType;

public class SearchParametersForDriving extends SearchParameters {
    private BodyType bodyType;

    public SearchParametersForDriving() {
        setCategory(VehicleCategory.DRIVING);
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public boolean hasBodyType() {
        return bodyType != null;
    }
}
