package zawadzki.marcin.model;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import zawadzki.marcin.exception.CustomException;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Car implements Comparable {

  private String model;

  private BigDecimal price;
  private CarColour colour;
  private Long mileage;
  private Set<String> components;

  private Car(CarBuilder carBuilder) {
    this.model = carBuilder.model;
    this.price = carBuilder.price;
    this.colour = carBuilder.colour;
    this.mileage = carBuilder.mileage;
    this.components = carBuilder.components;
  }

  public Car(String model, BigDecimal price, CarColour colour, Long mileage,
      Set<String> components) {
    this.model = model;
    this.price = price;
    this.colour = colour;
    this.mileage = mileage;
    this.components = components;
  }

  public static CarBuilder builder() {
    return new CarBuilder();
  }

  public void setModel(String model) {
    if (model.matches("^[A-Z\\s]+$")) {
      this.model = model;
    } else {
      throw new CustomException("Model accept only capital letter and white spaces");
    }
  }

  public void setPrice(BigDecimal price) {
    if (price.compareTo(ZERO) > 0) {
      this.price = price;
    } else {
      throw new CustomException("Price has to be greater than 0");
    }
  }

  public void setColour(CarColour colour) {
    if (colour.equals(CarColour.values())) {
      this.colour = colour;
    } else {
      throw new CustomException("Use colour value from enum");
    }
  }

  public void setMileage(Long mileage) {
    if (mileage > 0) {
      this.mileage = mileage;
    } else {
      throw new CustomException("Mileage should be greater than 0");
    }
  }

  public void setComponents(Set<String> components) {
    if (components.stream().allMatch(f -> f.matches("^[A-Z\\s]+$"))) {
      this.components = components;
    } else {
      System.out.println("Equipment should has only capital letters and white spaces");
    }
  }

  @Override
  public String toString() {
    return "\n" + "Car{" + "\n" +
        "model='" + model + '\'' + "\n" +
        "price=" + price + "\n" +
        "colour=" + colour + "\n" +
        "mileage=" + mileage + "\n" +
        "components=" + components + "\n" +
        '}';
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

  public static class CarBuilder {

    private static final String modelRegex = "^[A-Z\\s]+$";
    private String model;
    private BigDecimal price;
    private CarColour colour;
    private Long mileage;
    private Set<String> components;

    public CarBuilder model(String model) {
      try {
        if (StringUtils.isEmpty(model) || StringUtils.isBlank(model)) {
          throw new CustomException("Model cannot be null");
        }
        if (!model.matches(modelRegex)) {
          throw new CustomException("Model accept only capital letter and white spaces");
        }
        this.model = model;
        return this;
      } catch (Exception e) {
        throw new CustomException("Validation error for for model param");
      }
    }


    public CarBuilder price(BigDecimal price) {
      try {
        if (price == null) {
          throw new CustomException("Price cannot be null");
        }
        if (price.compareTo(ZERO) < 0) {
          throw new CustomException("XD");
        }
        this.price = price;
        return this;
      } catch (Exception e) {
        throw new CustomException("Validation error for price param");
      }
    }

    public CarBuilder colour(CarColour carColour) {
      try {
        if (StringUtils.isEmpty(carColour.name()) || StringUtils.isBlank(carColour.name())) {
          throw new CustomException("Colour cannot be null");
        }
        if (!Arrays.asList(CarColour.values()).contains(carColour)) {
          throw new CustomException("Use colour value from enum: RED, GREEN, BLACK, WHITE");
        }
        this.colour = carColour;
        return this;
      } catch (Exception e) {
        throw new CustomException("Validation error for colour param");
      }
    }

    public CarBuilder mileage(Long mileage) {
      try {
        if (Objects.isNull(mileage)) {
          throw new CustomException("Mileage cannot be null");
        }
        if (mileage < 0) {
          throw new CustomException("Mileage cannot be smaller than 0");
        }
        this.mileage = mileage;
        return this;
      } catch (Exception e) {
        throw new CustomException("Validation error for mileage");
      }
    }

    public CarBuilder components(Set<String> components) {
      try {
        if (components.isEmpty()) {
          throw new CustomException("Components list cannot be empty");
        }
        if (components.stream().noneMatch(f -> f.matches("^[A-Z\\s]+$"))) {
          throw new CustomException("Equipment should has only capital letters and white spaces");
        }
        this.components = components;
        return this;
      } catch (Exception e) {
        throw new CustomException("Validation error for components list");
      }
    }

    public Car build() {
      return new Car(this);
    }


  }
}
