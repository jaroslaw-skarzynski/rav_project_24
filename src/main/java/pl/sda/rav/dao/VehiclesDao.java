package pl.sda.rav.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sda.rav.domain.vehicles.Vehicle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class VehiclesDao {
    private final Logger logger = LoggerFactory.getLogger(VehiclesDao.class);

    private Set<Vehicle> vehicles = new HashSet<>();

    public Set<Vehicle> getVehicles() {
        return vehicles;
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
}