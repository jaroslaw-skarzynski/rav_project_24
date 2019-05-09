package pl.sda.rav;

import pl.sda.rav.domain.vehicles.Amphibian;
import pl.sda.rav.domain.vehicles.BodyType;
import pl.sda.rav.domain.vehicles.Car;
import pl.sda.rav.domain.vehicles.Motorboat;

import java.time.LocalDate;
import java.time.Month;

public class VehiclesSamples {
    public static final Car FORD_KUGA = new Car("CAR-123C", "Ford Kuga", LocalDate.of(2017, Month.JANUARY, 1), 800, BodyType.SUV);
    public static final Car TOYOTA_YARIS = new Car("CAR-155A", "Toyota Yaris", LocalDate.of(2018, Month.JANUARY, 1), 400, BodyType.HATCHBACK);
    public static final Motorboat SUN_FLOWER = new Motorboat("MOT-120", "Sun Flower 100", LocalDate.of(2018, Month.JANUARY, 1), 1000, 10);
    public static final Motorboat NIGHT_CRUISER = new Motorboat("MOT-123", "Night Cruiser", LocalDate.of(2015, Month.NOVEMBER, 1), 500, 4);
    public static final Amphibian AMPHIBIAN = new Amphibian("AMP-123", "AmfiB 2000", LocalDate.of(2010, Month.APRIL, 10), 200, 1000, 5);
}
