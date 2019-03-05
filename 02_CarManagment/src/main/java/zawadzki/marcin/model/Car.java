package zawadzki.marcin.model;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import zawadzki.marcin.enums.CarBodyType;
import zawadzki.marcin.enums.EngineType;
import zawadzki.marcin.exception.CustomCarException;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Car {

  private String model;
  private BigDecimal price;
  private Long mileage;
  private Engine engine;
  private CarBody carBody;
  private CarBodyType type;
  private Wheel wheel;

  private Car(CarBuilder carBuilder) {
    this.model = carBuilder.model;
    this.price = carBuilder.price;
    this.mileage = carBuilder.mileage;
    this.engine = carBuilder.engine;
    this.carBody = carBuilder.carBody;
    this.type = carBuilder.carBodyType;
    this.wheel = carBuilder.wheel;
  }

  public static CarBuilder builder() {
    return new CarBuilder();
  }

  public static class CarBuilder {

    private static final String modelRegex = "^[A-Z\\s]+$";
    private String model;
    private BigDecimal price;
    private Long mileage;
    private Engine engine;
    private CarBody carBody;
    private CarBodyType carBodyType;
    private Wheel wheel;


    public CarBuilder model(String model) {
      try {
        if (StringUtils.isEmpty(model) || StringUtils.isBlank(model)) {
          throw new CustomCarException("Model cannot be null");
        }
        if (!model.matches(modelRegex)) {
          throw new CustomCarException("Model accept only capital letter and white spaces");
        }
        this.model = model;
        return this;
      } catch (Exception e) {
        throw new CustomCarException("Validation error for for model param");
      }
    }


    public CarBuilder price(BigDecimal price) {
      try {
        if (price == null) {
          throw new CustomCarException("Price cannot be null");
        }
        if (price.compareTo(ZERO) < 0) {
          throw new CustomCarException("Price cannot be smaller than 0");
        }
        this.price = price;
        return this;
      } catch (Exception e) {
        throw new CustomCarException("Validation error for price param");
      }
    }

    public CarBuilder mileage(Long mileage) {
      try {
        if (Objects.isNull(mileage)) {
          throw new CustomCarException("Mileage cannot be null");
        }
        if (mileage < 0) {
          throw new CustomCarException("Mileage cannot be smaller than 0");
        }
        this.mileage = mileage;
        return this;
      } catch (Exception e) {
        throw new CustomCarException("Validation error for mileage");
      }
    }

    public CarBuilder engine(Engine engine) {
      try {
        if (Objects.isNull(engine)) {
          throw new CustomCarException("Engine cannot be null");
        }
        if (engine.getPower() < 0 || !Arrays.asList(EngineType.values())
            .contains(engine.getEngineType())) {
          throw new CustomCarException("Engine power smaller than 0 or EngineType is not matching");
        }
        this.engine = engine;
        return this;
      } catch (Exception e) {
        throw new CustomCarException("Validation failure");
      }
    }

    public CarBuilder carBody(CarBody carBody) {
      try {
        if (Objects.isNull(carBody)) {
          throw new CustomCarException("Car body  cannot be null");
        }
        this.carBody = carBody;
        return this;
      } catch (Exception e) {
        throw new CustomCarException("Validation error for car body");
      }
    }

    public CarBuilder type(CarBodyType carBodyType) {
      try {
        if (Objects.isNull(carBodyType)) {
          throw new CustomCarException("Car body type cannot be null");
        }
        this.carBodyType = carBodyType;
        return this;
      } catch (Exception e) {
        throw new CustomCarException("Validation error for car body type");
      }
    }

    public CarBuilder wheel(Wheel wheel) {
      try {
        if (Objects.isNull(wheel)) {
          throw new CustomCarException("Wheel cannot be null");
        }
        this.wheel = wheel;
        return this;
      } catch (Exception e) {
        throw new CustomCarException("Validation error for wheel");
      }
    }

    public Car build() {
      return new Car(this);
    }

  }
}
