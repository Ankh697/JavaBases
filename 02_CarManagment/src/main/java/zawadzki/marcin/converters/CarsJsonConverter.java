package zawadzki.marcin.converters;


import zawadzki.marcin.service.Cars;

public class CarsJsonConverter extends JsonConverter<Cars> {

  public CarsJsonConverter(String jsonFilename) {
    super(jsonFilename);
  }
}
