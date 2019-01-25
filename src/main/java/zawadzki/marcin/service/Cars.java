package zawadzki.marcin.service;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.beanutils.BeanComparator;
import org.eclipse.collections.impl.collector.Collectors2;
import zawadzki.marcin.converters.CarsJsonConverter;
import zawadzki.marcin.enums.SortType;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarColour;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

//@AllArgsConstructor
@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class Cars {
    private final Set<Car> cars;


    public Cars(String jsonFilename) {
        jsonFilename = "D:\\ProgramowanieJava\\JavaBases\\CarManagemetSystem\\cars_store.json";
        this.cars = new CarsJsonConverter(jsonFilename).fromJson().get().getCars();
    }

    @Override
    public String toString() {
        return cars.stream().map(c -> c.getModel() + " : " + c.getPrice()).collect(joining("\n"));
    }


    // KM
    public List<Car> sort(SortType sortType, boolean descending) {
        Stream<Car> carStream = null;

        switch (sortType) {
            case COLOR:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getColour));
                break;
            case MODEL:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getModel));
                break;
            case PRICE:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getPrice));
                break;
            default:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getMilage));
        }

        List<Car> sortedCars = carStream.collect(toList());
        if (descending) {
            Collections.reverse(sortedCars);
        }

        return sortedCars;
    }

    public List<Car> sort(Comparator<Car> comparator, boolean descending) {
        return descending ? cars.stream().sorted(comparator.reversed()).collect(toList()) : cars.stream().sorted(comparator).collect(toList());
    }

    public List<Car> sortMethod(SortType sortType, boolean descending) {
        if (sortType == null) {
            throw new RuntimeException("INCORRECT SORT TYPE");
        }

        switch (sortType) {
            case PRICE:
                return sort(Comparator.comparing(Car::getPrice), descending);
            case MODEL:
                return sort(Comparator.comparing(Car::getModel), descending);
            case COLOR:
                return sort(Comparator.comparing(Car::getColour), descending);
            default:
                return sort(Comparator.comparing(Car::getMilage), descending);
        }
    }

    public List<Car> findCarsWithMilageGreaterThan(Long milage) {
        return cars.stream().filter(p -> p.getMilage() > milage).collect(toList());
    }

    public Map<CarColour, Long> calculateNumberOfCarsForSpecifiedColor() {
//        return cars.stream().sorted().collect(groupingBy(Car::getColour, counting()));

        // MZ
        LinkedHashMap<CarColour, Long> reverseSortedMap = new LinkedHashMap<>();
        cars.stream().sorted().collect(groupingBy(Car::getColour, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        reverseSortedMap.forEach((k, v) -> System.out.println(k.name() + ":" + v));

        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getColour, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                        )
                );
    }

    public TreeMap<String, Set<BigDecimal>> theMostExpensiveCarForTheSameModel() {
        //TODO da sie tak?
//        return cars.stream().sorted().map(Car::getModel -> Comparator.comparing(Car::getPrice))
        return cars.stream().collect(groupingBy(Car::getModel, TreeMap::new, mapping(Car::getPrice, toSet())));
    }

    // STATYSTYKI AKURAT BEDZIESZ WYPISYWAL I BEDA VOID
    public void statisticsForCars() {
        System.out.println(cars.stream().collect(summarizingLong(Car::getMilage)).getAverage());
        System.out.println(cars.stream().collect(summarizingLong(Car::getMilage)).getMax());
        System.out.println(cars.stream().collect(summarizingLong(Car::getMilage)).getMin());


        System.out.println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getAverage());
        System.out.println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMax());
        System.out.println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMin());


        System.out.println("MIN PRICE: " + cars.stream().min(Comparator.comparing(Car::getPrice)).orElseThrow(IllegalStateException::new).getPrice());
        System.out.println("MIN PRICE: " + cars.stream().max(Comparator.comparing(Car::getPrice)).orElseThrow(IllegalStateException::new).getPrice());

        BigDecimal totalPrice = cars.stream().map(Car::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("AVG PRICE: " + totalPrice.divide(new BigDecimal(String.valueOf(cars.size())), 2, RoundingMode.CEILING));

    }

    public List<Car> carWithTheHighestPrice() {
        BigDecimal max = cars.stream().map(Car::getPrice).max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
        return cars.stream().filter(car -> car.getPrice().equals(max)).collect(Collectors.toList());
    }


    public Set<Car> carsWithSortedEquipment() {
//        return cars.stream().sorted(Comparator.comparing(c -> c.getComponents())).collect(toCollection(LinkedHashSet::new));

        cars.forEach(c -> c.setComponents(c.getComponents().stream().sorted(Comparator.reverseOrder()).collect(toCollection(LinkedHashSet::new))));
        return cars;
    }

    public Map<String, List<Car>> groupedByComponents() {
        Set<String> components = cars.stream().flatMap(c -> c.getComponents().stream()).collect(toSet());
        return components
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        comp -> cars.stream().filter(car -> car.getComponents().contains(comp)).collect(toList())
                        )
                );
    }

    public Set<Car> carsBetweenPrices(BigDecimal firstParam, BigDecimal secondParam) {
        return cars.stream().filter(car -> car.getPrice().compareTo(firstParam) > 0 && car.getPrice().compareTo(secondParam) < 0).collect(Collectors.toSet());
    }
}
