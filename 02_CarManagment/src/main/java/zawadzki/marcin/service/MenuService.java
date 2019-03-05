package zawadzki.marcin.service;

import static com.google.common.collect.Lists.newArrayList;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.enums.CarBodyColour;
import zawadzki.marcin.enums.CarBodyType;
import zawadzki.marcin.enums.EngineType;
import zawadzki.marcin.enums.SortType;
import zawadzki.marcin.enums.WheelType;
import zawadzki.marcin.exception.CustomCarException;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarBody;
import zawadzki.marcin.model.Engine;
import zawadzki.marcin.model.Wheel;

public class MenuService {

  private final String jsonFilename = "";
  private final Cars carsService = new Cars(jsonFilename);
  private final UserDataService userDataService = new UserDataService();


  public void mainMenu() {
    System.out.println("Car Management System - Module 2");
    System.out.println("Menu: ");
    System.out.println("1. Add new car to collection ");
    System.out.println("2. Print streams for cars collection ");
    System.out.println("3. Reinitialize car collection");
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

            Long mileage = userDataService.getLong();

            EngineType engineType = userDataService.getEngineType();

            Double power = userDataService.getPower();

            List<String> components = userDataService.getComponentsAsList();

            CarBodyColour carColour = userDataService.getCarBodyColour();

            CarBodyType carBodyType = userDataService.getCarBodyType();

            String wheelModel = userDataService.getWheelModel();

            Integer wheelSize = userDataService.getInt();

            WheelType wheelType = userDataService.getWheelType();

            Car carAdded = new Car(model, price, mileage, new Engine(engineType, power),
                new CarBody(carColour, components), carBodyType,
                new Wheel(wheelModel, wheelSize, wheelType));
            System.out.println("Added car");
            System.out.println(carAdded);

            Car carBuilder = Car.builder().model(model).price(price).mileage(mileage)
                .engine(new Engine(engineType, power)).carBody(new CarBody(carColour, components))
                .type(carBodyType).wheel(new Wheel(wheelModel, wheelSize, wheelType)).build();

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
            System.out.println("All cars for equipment");
            System.out.println("\n");
            System.out.println(carsService.allCarWithEquipment(newArrayList("AC")));
            System.out.println("Cars for specified fuel");
            System.out.println("\n");
            System.out.println(carsService.carModelForEngineType(EngineType.DIESEL));
            System.out.println("Cars for Car body type and between prices");
            System.out.println("\n");
            System.out.println(carsService
                .carsForSpecifiedBodyTypeAndPriceBetweenParams(CarBodyType.SEDAN,
                    new BigDecimal(40000), new BigDecimal(31312331)));
            System.out.println("Statistics");
            System.out.println("\n");
            carsService.statistics();
            System.out.println("Sorted by engine power - descending");
            System.out.println("\n");
            System.out.println(carsService.sort(SortType.ENGINE_POWER, true));
            System.out.println("Map: Key -> Car, Value -> Mileage ");
            System.out.println("\n");
            System.out.println(carsService.mapWithCarAndMileage());
            System.out.println("Car collection for specified wheel type");
            System.out.println("\n");
            System.out.println(carsService.wheelTypesForCarsCollection());
            break;

          case 3:
            String jsonFilename = "D:\\Programowanie\\JavaBases\\cars_storage.json";
            Set<Car> cars;
            cars = new CarsJsonConverter(jsonFilename).fromJson().get().getCars();
            System.out.println(cars);
            break;
          case 4:
            userDataService.close();
            return;
        }

      } catch (CustomCarException e) {
        e.getMessage();
      }
    }
  }

}
