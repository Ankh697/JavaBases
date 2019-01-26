package zawadzki.marcin.model;

import lombok.*;
import zawadzki.marcin.enums.CarBodyType;

import java.math.BigDecimal;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Car {
    private String model;
    private BigDecimal price;
    private Long mileage;
    private Engine engine;
    private CarBody carBody;
    private CarBodyType type;
    private Wheel wheel;
}
