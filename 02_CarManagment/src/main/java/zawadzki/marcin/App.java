package zawadzki.marcin;

import zawadzki.marcin.enums.CarBodyColour;
import zawadzki.marcin.enums.CarBodyType;
import zawadzki.marcin.enums.EngineType;
import zawadzki.marcin.enums.WheelType;
import zawadzki.marcin.model.Car;
import zawadzki.marcin.model.CarBody;
import zawadzki.marcin.model.Engine;
import zawadzki.marcin.model.Wheel;
import zawadzki.marcin.service.Cars;

import java.math.BigDecimal;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Cars carsStore = new Cars(newHashSet(
                Car.builder()
                        .model("BMW")
                        .price(BigDecimal.valueOf(300000))
                        .mileage(200L)
                        .carBody(new CarBody(CarBodyColour.BLACK, newArrayList("AC, GLASS ROOF, CARBON HOOD")))
                        .engine(new Engine(EngineType.DIESEL, 300))
                        .wheel(new Wheel("Steel", 20, WheelType.SUMMER))
                        .type(CarBodyType.HATCHBACK)
                        .build(),
                Car.builder()
                        .model("AUDI")
                        .price(BigDecimal.valueOf(350000))
                        .mileage(300L)
                        .carBody(new CarBody(CarBodyColour.RED, newArrayList("NITRO", "DSG", "SPORT SUSPENSION")))
                        .engine(new Engine(EngineType.DIESEL, 400))
                        .wheel(new Wheel("Alloy", 21, WheelType.SUMMER))
                        .type(CarBodyType.SEDAN)
                        .build(),
                Car.builder()
                        .model("KIA")
                        .price(BigDecimal.valueOf(310000))
                        .mileage(200L)
                        .carBody(new CarBody(CarBodyColour.GREEN, newArrayList("AC", "AMBIENT LIGHT", "SPORT EXHAUST")))
                        .engine(new Engine(EngineType.GASOLINE, 350))
                        .wheel(new Wheel("Alloy", 22, WheelType.SUMMER))
                        .type(CarBodyType.COMBI)
                        .build(),
                Car.builder()
                        .model("MERCEDES")
                        .price(BigDecimal.valueOf(250000))
                        .mileage(200L)
                        .carBody(new CarBody(CarBodyColour.RED, newArrayList("AMBIENT LIGHT", "LINE ASSIST", "CARBON HOOD")))
                        .engine(new Engine(EngineType.DIESEL, 300))
                        .wheel(new Wheel("Alloy", 20, WheelType.SUMMER))
                        .type(CarBodyType.HATCHBACK)
                        .build(),
                Car.builder()
                        .model("SEAT")
                        .price(BigDecimal.valueOf(200000))
                        .mileage(12000L)
                        .carBody(new CarBody(CarBodyColour.BLACK, newArrayList("AC", "GLASS ROOF", "CARBON HOOD")))
                        .engine(new Engine(EngineType.DIESEL, 310))
                        .wheel(new Wheel("Alloy", 22, WheelType.SUMMER))
                        .type(CarBodyType.SEDAN)
                        .build(),
                Car.builder()
                        .model("VOLKSVAGEN")
                        .price(BigDecimal.valueOf(300000))
                        .mileage(120000L)
                        .carBody(new CarBody(CarBodyColour.WHITE, newArrayList("NITRO", "DSG", "BLUETOOTH")))
                        .engine(new Engine(EngineType.DIESEL, 300))
                        .wheel(new Wheel("Alloy", 20, WheelType.SUMMER))
                        .type(CarBodyType.COMBI)
                        .build(),
                Car.builder()
                        .model("AUDI")
                        .price(BigDecimal.valueOf(400000))
                        .mileage(200L)
                        .carBody(new CarBody(CarBodyColour.SILVER, newArrayList("AMBIENT LIGHT", "SPORT PACKAGE", "SPORT EXHAUST")))
                        .engine(new Engine(EngineType.DIESEL, 300))
                        .wheel(new Wheel("Alloy", 22, WheelType.SUMMER))
                        .type(CarBodyType.SEDAN)
                        .build()
        ));
    }
}
