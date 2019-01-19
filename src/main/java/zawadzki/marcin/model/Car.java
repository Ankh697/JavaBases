package zawadzki.marcin.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class Car {
    private String model;

    private BigDecimal price;
    private CarColour carColour;
    private Long milage;
    private Set<String> components;

    public Car(String model, BigDecimal price, CarColour carColour, Long milage, Set<String> components) {
        this.model = model;
        this.price = price;
        this.carColour = carColour;
        this.milage = milage;
        this.components = components;
    }

/*    public Car(String model, BigDecimal price, CarColour carColour, Long milage, Set<String> components) {
        if (model.matches("^[A-Z\\s]+$")) {
            this.model = model;
        } else {
            System.out.println("Model accept only capital letter and white spaces");
        }
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            this.price = price;
        } else {
            System.out.println("Price has to be greater than 0");
        }
        if (carColour.equals(carColour)) {
            this.carColour = carColour;
        } else {
            System.out.println("Use colour value from enum");
        }
        if (milage > 0) {
            this.milage = milage;
        } else {
            System.out.println("Milage should be greater than 0");
        }
        if (components.stream().allMatch(f -> f.matches("^[A-Z\\s]+$"))) {
            this.components = components;
        } else {
            System.out.println("Equipment should has only capital letters and white spaces");
        }
    }*/
    public void setModel(String model) {
        if (model.matches("^[A-Z\\s]+$")) {
            this.model = model;
        } else {
            System.out.println("Model accept only capital letter and white spaces");
        }
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            this.price = price;
        } else {
            System.out.println("Price has to be greater than 0");
        }
    }

    public void setCarColour(CarColour carColour) {
        if (carColour.equals(carColour)) {
            this.carColour = carColour;
        } else {
            System.out.println("Use colour value from enum");
        }
    }

    public void setMilage(Long milage) {
        if (milage > 0) {
            this.milage = milage;
        } else {
            System.out.println("Milage should be greater than 0");
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
                "carColour=" + carColour + "\n" +
                "milage=" + milage + "\n" +
                "components=" + components + "\n" +
                '}';
    }
}
