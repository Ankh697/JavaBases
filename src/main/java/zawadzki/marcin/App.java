package zawadzki.marcin;

import zawadzki.marcin.converters.CarJsonConverter;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarColour;
import zawadzki.marcin.service.Cars;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.asList;

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

        System.out.println("Cars with milage greater than 10000 km");
        System.out.println(carsStore.findCarsWithMilageGreaterThan(10000L));

        System.out.println("Number of cars for specified color");
        System.out.println("\n");
        carsStore.calculateNumberOfCarsForSpecifiedColor();

        System.out.println("Car statistics");
        System.out.println("\n");
        carsStore.statisticsForCars();

        System.out.println("Car with the highest price");
        System.out.println(carsStore.carWithTheHighestPrice());

        System.out.println("Find the most expensive car for the same model");
        System.out.println("\n");
        System.out.println(carsStore.theMostExpensiveCarForTheSameModel());

        System.out.println("Find cars between prices");
        System.out.println("\n");
        System.out.println(carsStore.carsBetweenPrices(new BigDecimal(100000), new BigDecimal(300000)));

        System.out.println("===========Cars sorted by equipment===========");
        System.out.println("\n");
        System.out.println(carsStore.carsWithSortedEquipment());

        Scanner sc = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Car managment");

        while (true) {
            System.out.println("Menu ");
            System.out.println("1. Add new car to collection ");
            System.out.println("2. Print streams for cars collection ");
            System.out.println("3. Exit");
            System.out.println("Enter your choice ");

            int n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("You are currently adding new car to collection");
                    System.out.println("\n");
                    System.out.println("Type model param (only great letters and white spaces)");
                    String model = sc.next();
                    System.out.println("Type price param (price greater than 0)");
                    BigDecimal price = sc.nextBigDecimal();
                    System.out.println("Type colour param (Accepted colours from enum(BLACK, RED, GREEN, WHITE)");
                    CarColour colour = CarColour.valueOf(sc.next());
                    System.out.println("Type mileage param (Accepted greater than 0");
                    Long mileage = sc.nextLong();
                    System.out.println("Type components params (only great letters and white spaces)");
                    Set<String> componentsList = new HashSet<>();
                    String component = sc.nextLine();
                    while (!component.equals("null")) {
                        component = sc.nextLine();
                        componentsList.add(component);
                    }
                    Car carAdded = new Car(model, price, colour, mileage, componentsList);
                    carsStore.getCars().add(carAdded);

                    System.out.println("Below you can check parameters of your new brand car!");
                    System.out.println(carAdded);
                    System.out.println("Bellow you can check cars collection after car addition");
                    System.out.println(carsStore);
                    break;
                case 2:
                    System.out.println("Now you are printing stream operations for car collection");

                    System.out.println("Cars with milage greater than 10000 km");
                    System.out.println(carsStore.findCarsWithMilageGreaterThan(10000L));

                    System.out.println("Number of cars for specified color");
                    System.out.println("\n");
                    carsStore.calculateNumberOfCarsForSpecifiedColor();

                    System.out.println("Car statistics");
                    System.out.println("\n");
                    carsStore.statisticsForCars();

                    System.out.println("Car with the highest price");
                    System.out.println(carsStore.carWithTheHighestPrice());

                    System.out.println("Find the most expensive car for the same model");
                    System.out.println("\n");
                    System.out.println(carsStore.theMostExpensiveCarForTheSameModel());

                    System.out.println("Find cars between prices");
                    System.out.println("\n");
                    System.out.println(carsStore.carsBetweenPrices(new BigDecimal(100000), new BigDecimal(300000)));

                    System.out.println("===========Cars sorted by equipment===========");
                    System.out.println("\n");
                    System.out.println(carsStore.carsWithSortedEquipment());
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}

