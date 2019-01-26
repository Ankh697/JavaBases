package zawadzki.marcin.model;

import lombok.*;
import zawadzki.marcin.enums.WheelType;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Wheel {
    private String model;
    private int size;
    private WheelType wheelType;
}
