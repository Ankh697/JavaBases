package zawadzki.marcin;

import zawadzki.marcin.converters.CarJsonConverter;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarColour;
import zawadzki.marcin.service.Cars;

import java.math.BigDecimal;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Car car = Car.builder()
                .model("AUDI")
                .price(BigDecimal.valueOf(150000))
                .colour(CarColour.RED)
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
                        .colour(CarColour.BLACK)
                        .milage(30000L)
                        .components(newHashSet(asList("AA", "AB", "AC")))
                        .build(),
                Car.builder()
                        .model("BMW")
                        .price(BigDecimal.valueOf(200000))
                        .colour(CarColour.GREEN)
                        .milage(20000L)
                        .components(newHashSet(asList("BA", "BB", "BC")))
                        .build(),
                Car.builder()
                        .model("MAZDA")
                        .price(BigDecimal.valueOf(180000))
                        .colour(CarColour.RED)
                        .milage(35000L)
                        .components(newHashSet(asList("CC", "CB", "CC")))
                        .build(),
                Car.builder()
                        .model("TOYOTA")
                        .price(BigDecimal.valueOf(130000))
                        .colour(CarColour.BLACK)
                        .milage(10000L)
                        .components(newHashSet(asList("DA", "DB", "DC")))
                        .build(),
                Car.builder()
                        .model("AUDI")
                        .price(BigDecimal.valueOf(200000))
                        .colour(CarColour.WHITE)
                        .milage(4000L)
                        .components(newHashSet(asList("EA", "EB", "EC")))
                        .build(),
                Car.builder()
                        .model("KIA")
                        .price(BigDecimal.valueOf(210000))
                        .colour(CarColour.RED)
                        .milage(41000L)
                        .components(newHashSet(asList("FA", "FB", "FC")))
                        .build()));

        final String carsStoreJsonFilename = "cars_store.json";
        CarsJsonConverter carsJsonConverter = new CarsJsonConverter(carsStoreJsonFilename);
        carsJsonConverter.toJson(carsStore);
        carsJsonConverter.fromJson().ifPresent(System.out::println);

        System.out.println(carsStore.findCarsWithMilageGreaterThan(10000L));
        System.out.println("============COLOUR===============");
        System.out.println("\n");
        carsStore.calculateNumberOfCarsForSpecifiedColor();
        System.out.println("================================");
        System.out.println("\n");
        carsStore.statisticsForCars();
        System.out.println(carsStore.carWithTheHighestPrice());
        System.out.println("=================================");
        System.out.println("\n");
        System.out.println(carsStore.theMostExpensiveCarForTheSameModel());
        System.out.println("=================================");
        System.out.println("\n");
        System.out.println(carsStore.carsBetweenPrices(new BigDecimal(100000), new BigDecimal(300000)));
        System.out.println("===========SORTED===========");
        System.out.println("\n");
        System.out.println(carsStore.carsWithSortedEquipment());
    }
}
