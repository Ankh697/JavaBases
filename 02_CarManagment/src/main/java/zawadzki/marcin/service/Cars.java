package zawadzki.marcin.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.eclipse.collections.impl.collector.Collectors2;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.enums.CarBodyType;
import zawadzki.marcin.enums.EngineType;
import zawadzki.marcin.enums.SortType;
import zawadzki.marcin.enums.WheelType;
import zawadzki.marcin.model.Car;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class Cars {

  private final Set<Car> cars;

  Cars(String jsonFilename) {
    jsonFilename = "D:\\Programowanie\\JavaBases\\cars_storage.json";
    this.cars = new CarsJsonConverter(jsonFilename).fromJson().get().getCars();
  }


  List<Car> sort(SortType sortType, boolean descending) {
    Stream<Car> carStream;

    switch (sortType) {
      case COMPONENT_LIST:
        carStream = cars.stream()
            .sorted(Comparator.comparingInt(c -> c.getCarBody().getComponents().size()));
        break;
      case ENGINE_POWER:
        carStream = cars.stream().sorted(Comparator.comparing(c -> c.getEngine().getPower()));
        break;
      case WHEEL_SIZE:
        carStream = cars.stream().sorted(Comparator.comparing(c -> c.getWheel().getWheelType()));
        break;
      default:
        carStream = cars.stream().sorted(Comparator.comparing(Car::getPrice));
    }

    List<Car> sortedCars = carStream.collect(toList());
    if (descending) {
      Collections.reverse(sortedCars);
    }

    return sortedCars;
  }

  List<Car> carsForSpecifiedBodyTypeAndPriceBetweenParams(CarBodyType carBodyType,
      BigDecimal minPrice, BigDecimal maxPrice) {
    return cars.stream().filter(car -> car.getModel().equals(carBodyType)).filter(
        car -> car.getPrice().compareTo(minPrice) > 0 && car.getPrice().compareTo(maxPrice) < 0)
        .collect(Collectors.toList());
  }

  List<Car> carModelForEngineType(EngineType engineType) {
    return cars.stream().filter(c -> c.getEngine().getEngineType().equals(engineType))
        .sorted(Comparator.comparing(Car::getModel)).collect(Collectors.toList());
  }

  void statistics() {
    System.out.println(cars.stream().collect(Collectors.summarizingLong(Car::getMileage)).getMin());
    System.out.println(cars.stream().collect(Collectors.summarizingLong(Car::getMileage)).getMax());
    System.out
        .println(cars.stream().collect(Collectors.summarizingLong(Car::getMileage)).getAverage());

    System.out
        .println(cars.stream().collect(Collectors.summarizingDouble(Car::getMileage)).getMin());
    System.out
        .println(cars.stream().collect(Collectors.summarizingDouble(Car::getMileage)).getMax());
    System.out
        .println(cars.stream().collect(Collectors.summarizingDouble(Car::getMileage)).getAverage());

    System.out
        .println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMin());
    System.out
        .println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMax());
    System.out.println(
        cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getAverage());
  }


  Map<Car, Long> mapWithCarAndMileage() {
    return cars.stream().collect(toMap(car -> car, Car::getMileage)).entrySet().stream()
        .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (c1, v2) -> c1, LinkedHashMap::new));
  }

  public Map<WheelType, List<Car>> wheelTypesForCarsCollection() {
    return cars.stream()
        .collect(groupingBy(car -> car.getWheel().getWheelType(), Collectors.toList()));
  }


  List<Car> allCarWithEquipment(List<String> components) {
    return cars.stream().filter(car -> car.getCarBody().getComponents().containsAll(components))
        .sorted(
            Comparator.comparing(Car::getModel).reversed()).collect(Collectors.toList());
  }

}
