package zawadzki.marcin;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import zawadzki.marcin.converters.CarJsonConverter;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarColour;
import zawadzki.marcin.service.CarsService;
import zawadzki.marcin.service.MenuService;

public class App {

  public static void main(String[] args) {

    new MenuService().mainMenu();

    Car car = Car.builder()
        .model("AUDI")
        .price(BigDecimal.valueOf(150000))
        .colour(CarColour.RED)
        .components(newHashSet("CA", "CB", "CC", "CD"))
        .mileage(20000L)
        .build();

    final String carJsonFilename = "car.json";
    CarJsonConverter carJsonConverter = new CarJsonConverter(carJsonFilename);
    carJsonConverter.toJson(car);
    carJsonConverter.fromJson().ifPresent(System.out::println);

    CarsService carsServiceStore = new CarsService(newHashSet(
        Car.builder()
            .model("AUDI")
            .price(BigDecimal.valueOf(300000))
            .colour(CarColour.BLACK)
            .mileage(30000L)
            .components(newHashSet(asList("AA", "AB", "AC")))
            .build(),
        Car.builder()
            .model("BMW")
            .price(BigDecimal.valueOf(200000))
            .colour(CarColour.GREEN)
            .mileage(20000L)
            .components(newHashSet(asList("BA", "BB", "BC")))
            .build(),
        Car.builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(180000))
            .colour(CarColour.RED)
            .mileage(35000L)
            .components(newHashSet(asList("CC", "CB", "CC")))
            .build(),
        Car.builder()
            .model("TOYOTA")
            .price(BigDecimal.valueOf(130000))
            .colour(CarColour.BLACK)
            .mileage(10000L)
            .components(newHashSet(asList("DA", "DB", "DC")))
            .build(),
        Car.builder()
            .model("AUDI")
            .price(BigDecimal.valueOf(200000))
            .colour(CarColour.WHITE)
            .mileage(4000L)
            .components(newHashSet(asList("EA", "EB", "EC")))
            .build(),
        Car.builder()
            .model("KIA")
            .price(BigDecimal.valueOf(210000))
            .colour(CarColour.RED)
            .mileage(41000L)
            .components(newHashSet(asList("FA", "FB", "FC")))
            .build()));

    final String carsStoreJsonFilename = "cars_store.json";
    CarsJsonConverter carsJsonConverter = new CarsJsonConverter(carsStoreJsonFilename);
    carsJsonConverter.toJson(carsServiceStore);
    carsJsonConverter.fromJson().ifPresent(System.out::println);

  }
}

