package zawadzki.marcin.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zawadzki.marcin.exception.CustomException;

import java.math.BigDecimal;
import java.util.Set;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class Car implements Comparable {
    private String model;

    private BigDecimal price;
    private CarColour colour;
    private Long milage;
    private Set<String> components;

    public Car(String model, BigDecimal price, CarColour colour, Long milage, Set<String> components) {
        this.model = model;
        this.price = price;
        this.colour = colour;
        this.milage = milage;
        this.components = components;
    }

    public void setModel(String model) {
        if (model.matches("^[A-Z\\s]+$")) {
            this.model = model;
        } else {
            throw new CustomException("Model accept only capital letter and white spaces");
        }
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) > 0) {
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

    public void setMilage(Long milage) {
        if (milage > 0) {
            this.milage = milage;
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
                "milage=" + milage + "\n" +
                "components=" + components + "\n" +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
