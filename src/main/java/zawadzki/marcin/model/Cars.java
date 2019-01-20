package zawadzki.marcin.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.collections.impl.collector.Collectors2;
import zawadzki.marcin.converters.CarsJsonConverter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Cars {
    Set<Car> cars;

    public Cars(Set<Car> cars) {
        this.cars = new CarsJsonConverter("D:\\ProgramowanieJava\\JavaBases\\CarManagemetSystem\\cars_store.json").fromJson().get().getCars();
    }

    @Override
    public String toString() {
        return "Cars{" +
                "cars=" + cars +
                '}';
    }

    public static Comparator<Car> carMilageComparator = Comparator.comparing(Car::getMilage);
    public static Comparator<Car> carPriceComparator = Comparator.comparing(Car::getPrice);
    public static Comparator<Car> carModelComparator = Comparator.comparing(Car::getModel);
    public static Comparator<Car> carColourComparator = Comparator.comparing(Car::getCarColour);


    public List<Car> findCarsWithMilageGreaterThan(Long milage) {
        return cars.stream().filter(p -> p.getMilage() > milage).collect(toList());
    }

    public void calculateNumberOfCarsForSpecifiedColor() {
//        return cars.stream().sorted().collect(groupingBy(Car::getCarColour, counting()));
        LinkedHashMap<CarColour, Long> reverseSortedMap = new LinkedHashMap<>();
        cars.stream().sorted().collect(groupingBy(Car::getCarColour, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        reverseSortedMap.forEach((k, v) -> System.out.println(k.name() + ":" + v));
    }

    public TreeMap<String, Set<BigDecimal>> theMostExpensiveCarForTheSameModel() {
//        return cars.stream().sorted().map(Car::getModel -> Comparator.comparing(Car::getPrice))
        return cars.stream().collect(groupingBy(Car::getModel, TreeMap::new, mapping(Car::getPrice, toSet())));
    }

    public void statisticsForCars() {
        System.out.println(cars.stream().collect(summarizingLong(Car::getMilage)).getAverage());
        System.out.println(cars.stream().collect(summarizingLong(Car::getMilage)).getMax());
        System.out.println(cars.stream().collect(summarizingLong(Car::getMilage)).getMin());


        System.out.println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getAverage());
        System.out.println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMax());
        System.out.println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMin());
    }

    public List<Car> carWithTheHighestPrice() {
        BigDecimal max = cars.stream().map(Car::getPrice).max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        return cars.stream().filter(car -> car.getPrice().equals(max)).collect(Collectors.toList());
    }


    public void carsWithSortedEquipment() {
//        return cars.stream().sorted(Comparator.comparing(Car::getComponents)).collect(toCollection(LinkedHashSet::new));
        cars.forEach(c -> c.getComponents().stream().sorted(Comparator.reverseOrder()));
        cars.forEach(car -> car.getComponents().forEach(System.out::println));
    }


/*    public Map<String, List<Car>> jesus() {

    }*/

    public Set<Car> carsBetweenPrices(BigDecimal firstParam, BigDecimal secondParam) {
        return cars.stream().filter(car -> car.getPrice().compareTo(firstParam) > 0 && car.getPrice().compareTo(secondParam) < 0).collect(Collectors.toSet());
    }
}
