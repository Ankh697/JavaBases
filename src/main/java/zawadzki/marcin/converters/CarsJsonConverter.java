package zawadzki.marcin.converters;

import zawadzki.marcin.service.CarsService;

public class CarsJsonConverter extends JsonConverter<CarsService> {
    public CarsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
