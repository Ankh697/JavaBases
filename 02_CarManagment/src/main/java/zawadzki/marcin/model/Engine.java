package zawadzki.marcin.model;

import lombok.*;
import zawadzki.marcin.enums.EngineType;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Engine {
    private EngineType engineType;
    private double power;
}
