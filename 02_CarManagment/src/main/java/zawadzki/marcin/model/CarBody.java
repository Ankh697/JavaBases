package zawadzki.marcin.model;

import lombok.*;
import zawadzki.marcin.enums.CarBodyColour;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CarBody {
    private CarBodyColour carBodyColour;
    private List<String> components;
}
