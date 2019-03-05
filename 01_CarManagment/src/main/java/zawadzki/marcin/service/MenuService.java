package zawadzki.marcin.service;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.enums.SortType;
import zawadzki.marcin.exception.CustomException;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarColour;

public class MenuService {

  private final String jsonFilename = "";
  private final CarsService carsService = new CarsService(jsonFilename);
  private final UserDataService userDataService = new UserDataService();


  public void mainMenu() {
    System.out.println("Car Management System");
    System.out.println("Menu ");
    System.out.println("1. Add new car to collection ");
    System.out.println("2. Print streams for cars collection ");
    System.out.println("3. Reinitialize collection");
    System.out.println("4. Exit");
    System.out.println("Enter your choice ");
    Scanner sc = new Scanner(System.in);
    while (true) {
      int option = sc.nextInt();
      try {
        switch (option) {
          case 1:
            System.out.println("You are currently adding new car to collection");
            System.out.println("\n");
            String model = userDataService
                .getString();

            BigDecimal price = userDataService
                .getBigDecimal();

            CarColour carColour = userDataService.getCarColour(
            );

            Set<String> components = userDataService
                .getComponents();

            Long mileage = userDataService.getLong();

            Car carAdded = new Car(model, price, carColour, mileage, components);
            System.out.println("Added car");
            System.out.println(carAdded);

            Car carBuilder = Car.builder().model(model).price(price).colour(carColour)
                .components(components).mileage(mileage).build();

            System.out.println("Added car with builder usage");
            System.out.println(carBuilder);

            carsService.getCars().add(carAdded);
            carsService.getCars().add(carBuilder);
            System.out.println("Your added car below");
            System.out.println(carAdded);
            System.out.println("Car list below");
            System.out.println(carsService.getCars());

            break;

          case 2:
            System.out.println("Now you are printing stream operations for car collection");

            System.out.println("CarsService with mileage greater than 10000 km");
            System.out.println(carsService.findCarsWithMileageGreaterThan(10000L));

            System.out.println("Number of cars for specified color");
            System.out.println("\n");
            carsService.calculateNumberOfCarsForSpecifiedColor();

            System.out.println("Car statistics");
            System.out.println("\n");
            carsService.statisticsForCars();

            System.out.println("Car with the highest price");
            System.out.println(carsService.carWithTheHighestPrice());

            System.out.println("Find the most expensive car for the same model");
            System.out.println("\n");
            System.out.println(carsService.theMostExpensiveCarForTheSameModel());

            System.out.println("Find cars between prices");
            System.out.println("\n");
            System.out.println(
                carsService.carsBetweenPrices(new BigDecimal(100000), new BigDecimal(300000)));

            System.out.println("===========CarsService sorted by equipment===========");
            System.out.println("\n");
            System.out.println(carsService.carsWithSortedEquipment());

            System.out.println("===========CarsService sorted by price===========");
            System.out.println("\n");
            System.out.println(carsService.sort(SortType.PRICE, false));

            System.out
                .println("===========CarsService sorted by Model with descending order===========");
            System.out.println("\n");
            System.out.println(carsService.sort(SortType.MODEL, true));

            System.out
                .println("===========CarsService number of cars for specified colour===========");
            System.out.println("\n");
            System.out.println(carsService.calculateNumberOfCarsForSpecifiedColor());

            System.out
                .println("===========CarsService sorted color with descending order===========");
            System.out.println("\n");
            System.out.println(carsService.sortMethod(SortType.COLOR, true));

            System.out.println("===========CarsService grouped by components===========");
            System.out.println("\n");
            System.out.println(carsService.groupedByCockonents());
            break;

          case 3:
            String jsonFilename = "D:\\Programowanie\\JavaBasics\\JavaBases\\cars_store.json";
            Set<Car> cars;
            cars = new CarsJsonConverter(jsonFilename).fromJson().get().getCars();
            System.out.println(cars);
            break;
          case 4:
            userDataService.close();
            return;
        }

      } catch (CustomException e) {
        e.getMessage();
      }
    }
  }

  // ....
}
