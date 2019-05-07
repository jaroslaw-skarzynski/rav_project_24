package pl.sda.rav.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sda.rav.domain.vehicles.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class VehiclesDao {
    private final Logger logger = LoggerFactory.getLogger(VehiclesDao.class);

    private Set<Vehicle> vehicles = new HashSet<>();

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Set<Vehicle> searchVehicles(SearchParameters searchParameters) {
        Set<Vehicle> foundVehicles = new TreeSet<>();
        for (Vehicle vehicle : vehicles) {
            if(searchParameters.hasCategory()) {
                if(!isVehicleOfCategory(vehicle, searchParameters.getCategory())) {
                    continue;
                }
            }

            if(searchParameters.hasStatus()) {
                if(!isVehicleOfStatus(vehicle, searchParameters.getStatus())) {
                    continue;
                }
            }

            if(searchParameters.hasAge()) {
                if(!isVehicleOfAge(vehicle, searchParameters.getAge())) {
                    continue;
                }
            }

            if(searchParameters instanceof SearchParametersForDriving && vehicle instanceof DrivingVehicle) {
                SearchParametersForDriving searchParametersForDriving = (SearchParametersForDriving) searchParameters;
                DrivingVehicle drivingVehicle = (DrivingVehicle) vehicle;
                if(searchParametersForDriving.hasBodyType()) {
                    if(!isVehicleOfBodyType(drivingVehicle, searchParametersForDriving.getBodyType())) {
                        continue;
                    }
                }
            }

            if(searchParameters instanceof SearchParametersForSwimming && vehicle instanceof SwimmingVehicle) {
                SearchParametersForSwimming searchParametersForSwimming = (SearchParametersForSwimming) searchParameters;
                SwimmingVehicle swimmingVehicle = (SwimmingVehicle) vehicle;
                if(searchParametersForSwimming.hasDisplacementFrom() || searchParametersForSwimming.hasDisplacementTo()) {
                    if(!isVehicleOfDisplacement(swimmingVehicle, searchParametersForSwimming.getDisplacementFrom(), searchParametersForSwimming.getDisplacementTo())) {
                        continue;
                    }
                }
            }

            foundVehicles.add(vehicle);
        }

        return foundVehicles;
    }

    public boolean add(Vehicle vehicle) {
        if(vehicles.contains(vehicle)) {
            logger.error("Vehicle already exists in DAO, vehicle: {}", vehicle);
            return false;
        }

        vehicles.add(vehicle);
        return true;
    }

    public boolean remove(String vin) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        while(iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if(vehicle.getVin().equals(vin)) {
                return true;
            }
        }

        logger.warn("There is no vehicle (to remove) of vin: {}", vin);
        return false;
    }

    private boolean isVehicleOfCategory(Vehicle vehicle, VehicleCategory category) {
        switch (category) {
            case DRIVING:
                return vehicle instanceof DrivingVehicle;
            case SWIMMING:
                return vehicle instanceof DrivingVehicle;
            case FLYING:
                return vehicle instanceof FlyingVehicle;
            default:
                return true;
        }
    }

    private boolean isVehicleOfStatus(Vehicle vehicle, VehicleStatus status) {
        switch (status) {
            case AVAILABLE:
                return vehicle.getStatus().isAvailable();
            case UNAVAILABLE:
                return !vehicle.getStatus().isAvailable();
            default:
                return false;
        }
    }

    private boolean isVehicleOfAge(Vehicle vehicle, VehicleAge age) {
        int vehicleAge = LocalDate.now().getYear() - vehicle.getProductionDate().getYear();

        if(vehicleAge <= 5) {
            return age == VehicleAge.MIDDLE;
        }

        if(vehicleAge <= 2) {
            return age == VehicleAge.YOUNG;
        }

        return age == VehicleAge.OLD;
    }

    private boolean isVehicleOfBodyType(DrivingVehicle drivingVehicle, BodyType bodyType) {
        return drivingVehicle.getBodyType() == bodyType;
    }

    private boolean isVehicleOfDisplacement(SwimmingVehicle swimmingVehicle, Integer displacementFrom, Integer displacementTo) {
        int displacement = swimmingVehicle.getDisplacement();
        return displacement >= displacementFrom && displacement <= displacementTo;
    }
}
