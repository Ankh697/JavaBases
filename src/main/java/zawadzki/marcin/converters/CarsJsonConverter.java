package zawadzki.marcin.converters;

import zawadzki.marcin.model.Cars;

public class CarsJsonConverter extends JsonConverter<Cars> {
    public CarsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
