package pl.sda.rav.dao.vehicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rav.VehiclesSamples;
import pl.sda.rav.dao.orders.OrdersDao;
import pl.sda.rav.domain.Period;
import pl.sda.rav.domain.vehicles.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VehiclesDaoIntegrationTest {
    private VehiclesDao vehiclesDao;

    @BeforeEach
    void setUp() {
        vehiclesDao = new VehiclesDao(new OrdersDao());
        vehiclesDao.add(VehiclesSamples.FORD_KUGA);
        vehiclesDao.add(VehiclesSamples.TOYOTA_YARIS);
        vehiclesDao.add(VehiclesSamples.NIGHT_CRUISER);
        vehiclesDao.add(VehiclesSamples.AMPHIBIAN);
        vehiclesDao.add(VehiclesSamples.SUN_FLOWER);
    }

    @Test
    public void shouldGetAllVehicles() {
        // given
        // when
        Set<Vehicle> vehicles = vehiclesDao.getVehicles();

        // then
        assertEquals(5, vehicles.size());
        assertIterableEquals(Arrays.asList(VehiclesSamples.AMPHIBIAN, VehiclesSamples.NIGHT_CRUISER, VehiclesSamples.FORD_KUGA, VehiclesSamples.SUN_FLOWER, VehiclesSamples.TOYOTA_YARIS), vehicles);
    }

    @Test
    public void shouldAddAnotherVehicle() {
        // given
        Vehicle newVehicle = new Motorboat("MOT-456", "SunShine-2", LocalDate.of(2019, Month.NOVEMBER, 1), 650, 10);

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
        Vehicle newVehicle = new Motorboat(VehiclesSamples.NIGHT_CRUISER.getVin(), "SunShine-2", LocalDate.of(2019, Month.NOVEMBER, 1), 650, 10);

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
        boolean removed = vehiclesDao.remove(VehiclesSamples.FORD_KUGA.getVin());
        Set<Vehicle> vehicles = vehiclesDao.getVehicles();

        // then
        assertTrue(removed);
        assertEquals(4, vehicles.size());
        assertFalse(vehicles.contains(VehiclesSamples.FORD_KUGA));
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

    @Test
    public void shouldFindOnlyDrivingVehicles() {
        // given
        SearchParameters searchParameters = new SearchParametersForDriving();

        // when
        Set<Vehicle> vehicles = vehiclesDao.searchVehicles(searchParameters);

        // then
        assertIterableEquals(Arrays.asList(VehiclesSamples.AMPHIBIAN, VehiclesSamples.FORD_KUGA, VehiclesSamples.TOYOTA_YARIS), vehicles);
    }

    @Test
    public void shouldFindOnlySwimmingVehicles() {
        // given
        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setCategory(VehicleCategory.SWIMMING);

        // when
        Set<Vehicle> vehicles = vehiclesDao.searchVehicles(searchParameters);

        // then
        assertIterableEquals(Arrays.asList(VehiclesSamples.AMPHIBIAN, VehiclesSamples.NIGHT_CRUISER, VehiclesSamples.SUN_FLOWER), vehicles);
    }

    @Test
    public void shouldFindOnlyAvailableVehicles() {
        // given
        SearchParameters searchParameters = new SearchParameters();
        LocalDate startDate = LocalDate.of(2019, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2019, Month.JULY, 10);
        searchParameters.setPeriodToCheck(new Period(startDate, endDate));

        // when
        Set<Vehicle> vehicles = vehiclesDao.searchVehicles(searchParameters);

        // then
        assertIterableEquals(Arrays.asList(VehiclesSamples.FORD_KUGA, VehiclesSamples.SUN_FLOWER, VehiclesSamples.TOYOTA_YARIS), vehicles);
    }

    @Test
    public void shouldFindOnlyYoungVehicles() {
        // given
        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setAge(VehicleAge.YOUNG);

        // when
        Set<Vehicle> vehicles = vehiclesDao.searchVehicles(searchParameters);

        // then
        assertIterableEquals(Arrays.asList(VehiclesSamples.FORD_KUGA, VehiclesSamples.SUN_FLOWER, VehiclesSamples.TOYOTA_YARIS), vehicles);
    }

    @Test
    public void shouldFindOnlyHatchbacks() {
        // given
        SearchParametersForDriving searchParameters = new SearchParametersForDriving();
        searchParameters.setBodyType(BodyType.HATCHBACK);

        // when
        Set<Vehicle> vehicles = vehiclesDao.searchVehicles(searchParameters);

        // then
        assertIterableEquals(Arrays.asList(VehiclesSamples.TOYOTA_YARIS), vehicles);
    }

    @Test
    public void shouldFindOnlyVehiclesWithCertainDisplacement() {
        // given
        SearchParametersForSwimming searchParameters = new SearchParametersForSwimming();
        searchParameters.setDisplacementFrom(5);

        // when
        Set<Vehicle> vehicles = vehiclesDao.searchVehicles(searchParameters);

        // then
        assertIterableEquals(Arrays.asList(VehiclesSamples.AMPHIBIAN, VehiclesSamples.SUN_FLOWER), vehicles);
    }
}