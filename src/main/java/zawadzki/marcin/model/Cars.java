package zawadzki.marcin.model;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Cars {
    Set<Car> cars;

    @Override
    public String toString() {
        return "Cars{" +
                "cars=" + cars +
                '}';
    }
}
