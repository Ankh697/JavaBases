package zawadzki.marcin;

import javafx.scene.paint.Color;
import zawadzki.marcin.converters.CarJsonConverter;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarColour;
import zawadzki.marcin.model.Cars;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Car car = Car.builder()
                .model("AUDI")
                .price(BigDecimal.valueOf(150000))
                .carColour(CarColour.RED)
                .components(newHashSet("CA", "CB", "CC", "CD"))
                .milage(20000L)
                .build();


        final String carJsonFilename = "car.json";
        CarJsonConverter carJsonConverter = new CarJsonConverter(carJsonFilename);
        carJsonConverter.toJson(car);
        carJsonConverter.fromJson().ifPresent(System.out::println);




        Cars carsStore = new Cars(newHashSet(
                Car.builder()
                        .model("AUDI")
                        .price(BigDecimal.valueOf(300000))
                        .carColour(CarColour.BLACK)
                        .milage(30000L)
                        .components(newHashSet(Arrays.asList("AA", "AB", "AC")))
                        .build(),
                Car.builder()
                        .model("BMW")
                        .price(BigDecimal.valueOf(200000))
                        .carColour(CarColour.GREEN)
                        .milage(20000L)
                        .components(new HashSet<>(Arrays.asList("BA", "BB", "BC")))
                        .build(),
                Car.builder()
                        .model("MAZDA")
                        .price(BigDecimal.valueOf(180000))
                        .carColour(CarColour.RED)
                        .milage(35000L)
                        .components(new HashSet<>(Arrays.asList("CC", "CB", "CC")))
                        .build(),
                Car.builder()
                        .model("TOYOTA")
                        .price(BigDecimal.valueOf(130000))
                        .carColour(CarColour.BLACK)
                        .milage(10000L)
                        .components(new HashSet<>(Arrays.asList("DA", "DB", "DC")))
                        .build(),
                Car.builder()
                        .model("AUDI")
                        .price(BigDecimal.valueOf(200000))
                        .carColour(CarColour.WHITE)
                        .milage(4000L)
                        .components(new HashSet<>(Arrays.asList("EA", "EB", "EC")))
                        .build(),
                Car.builder()
                        .model("KIA")
                        .price(BigDecimal.valueOf(210000))
                        .carColour(CarColour.RED)
                        .milage(41000L)
                        .components(new HashSet<>(Arrays.asList("FA", "FB", "FC")))
                        .build()));

        final String carsStoreJsonFilename = "cars_store.json";
        CarsJsonConverter carsJsonConverter = new CarsJsonConverter(carsStoreJsonFilename);
        carsJsonConverter.toJson(carsStore);
        carsJsonConverter.fromJson().ifPresent(System.out::println);
    }
}
