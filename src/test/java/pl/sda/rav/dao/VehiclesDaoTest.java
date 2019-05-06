package pl.sda.rav.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rav.domain.vehicles.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VehiclesDaoTest {
    private VehiclesDao vehiclesDao;
    private Car fordKuga = new Car("CAR-123C", Status.AVAILABLE, "Ford Kuga", LocalDate.of(2017, Month.JANUARY, 1), 800, BodyType.SUV);
    private Car toyotaYaris = new Car("CAR-155A", Status.AVAILABLE, "Toyota Yaris", LocalDate.of(2018, Month.JANUARY, 1), 400, BodyType.HATCHBACK);
    private Motorboat sunFlower = new Motorboat("MOT-120", Status.AVAILABLE, "Sun Flower 100", LocalDate.of(2018, Month.JANUARY, 1), 1000, 10);
    private Motorboat nightCruiser = new Motorboat("MOT-123", Status.IN_REPAIR, "Night Cruiser", LocalDate.of(2015, Month.NOVEMBER, 1), 500, 4);
    private Amphibian amphibian = new Amphibian("AMP-123", Status.RENT, "AmfiB 2000", LocalDate.of(2010, Month.APRIL, 10), 200, 1000, 5);

    @BeforeEach
    void setUp() {
        vehiclesDao = new VehiclesDao();
        vehiclesDao.add(fordKuga);
        vehiclesDao.add(toyotaYaris);
        vehiclesDao.add(nightCruiser);
        vehiclesDao.add(amphibian);
        vehiclesDao.add(sunFlower);
    }

    @Test
    public void shouldGetAllVehicles() {
        // given
        // when
        Set<Vehicle> vehicles = vehiclesDao.getVehicles();

        // then
        assertEquals(5, vehicles.size());
        assertIterableEquals(Arrays.asList(amphibian, nightCruiser, fordKuga, sunFlower, toyotaYaris), vehicles);
    }

    @Test
    public void shouldAddAnotherVehicle() {
        // given
        Vehicle newVehicle = new Motorboat("MOT-456", Status.AVAILABLE, "SunShine-2", LocalDate.of(2019, Month.NOVEMBER, 1), 650, 10);

        // when
        boolean added = vehiclesDao.add(newVehicle);
        Set<Vehicle> vehicles = vehiclesDao.getVehicles();

        // then
        assertTrue(added);
        assertEquals(6, vehicles.size());
        assertTrue(vehicles.contains(newVehicle));
    }

    @Test
    public void shouldNotAddDuplicateVehicle() {
        // given
        Vehicle newVehicle = new Motorboat(nightCruiser.getVin(), Status.AVAILABLE, "SunShine-2", LocalDate.of(2019, Month.NOVEMBER, 1), 650, 10);

        // when
        boolean added = vehiclesDao.add(newVehicle);
        Set<Vehicle> vehicles = vehiclesDao.getVehicles();

        // then
        assertFalse(added);
        assertEquals(5, vehicles.size());
    }

    @Test
    public void shouldRemoveExistingVehicle() {
        // given
        // when
        boolean removed = vehiclesDao.remove(fordKuga.getVin());
        Set<Vehicle> vehicles = vehiclesDao.getVehicles();

        // then
        assertTrue(removed);
        assertEquals(4, vehicles.size());
        assertFalse(vehicles.contains(fordKuga));
    }

    @Test
    public void shouldNotRemoveNotExistingVehicle() {
        // given
        // when
        boolean removed = vehiclesDao.remove("XYZ");
        Set<Vehicle> vehicles = vehiclesDao.getVehicles();

        // then
        assertFalse(removed);
        assertEquals(5, vehicles.size());
    }
}