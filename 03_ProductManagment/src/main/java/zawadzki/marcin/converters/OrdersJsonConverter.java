package zawadzki.marcin.converters;


import zawadzki.marcin.service.Orders;

public class OrdersJsonConverter extends JsonConverter<Orders> {

  public OrdersJsonConverter(String jsonFilename) {
    super(jsonFilename);
  }
}
