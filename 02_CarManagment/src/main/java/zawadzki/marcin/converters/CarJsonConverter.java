package zawadzki.marcin.converters;


import zawadzki.marcin.model.Car;

public class CarJsonConverter extends JsonConverter<Car> {
    public CarJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
