package zawadzki.marcin.service;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zawadzki.marcin.enums.CarBodyType;
import zawadzki.marcin.enums.EngineType;
import zawadzki.marcin.enums.SortType;
import zawadzki.marcin.model.Car;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class Cars {
    private final Set<Car> cars;

    // DOROBI KOLEJ

    public List<Car> sort(SortType sortType, boolean descending) {
        Stream<Car> carStream = null;

        switch (sortType) {
            case COMPONENT_LIST:
                carStream = cars.stream().sorted(Comparator.comparingInt(c -> c.getCarBody().getComponents().size()));
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

    public List<Car> carsForSpecifiedBodyTypeAndPriceBetweenParams(CarBodyType carBodyType, BigDecimal minPrice, BigDecimal maxPrice) {
        return cars.stream().filter(car -> car.getModel().equals(carBodyType)).filter(car -> car.getPrice().compareTo(minPrice) > 0 && car.getPrice().compareTo(maxPrice) < 0).collect(Collectors.toList());
    }

    public List<Car> carModelForEngineType(EngineType engineType) {
        return cars.stream().filter(c -> c.getEngine().getEngineType().equals(engineType)).sorted(Comparator.comparing(Car::getModel)).collect(Collectors.toList());
    }


}
