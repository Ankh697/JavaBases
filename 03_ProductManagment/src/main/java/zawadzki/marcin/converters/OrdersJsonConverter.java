package zawadzki.marcin.converters;


import zawadzki.marcin.model.Orders;

public class OrdersJsonConverter extends JsonConverter<Orders> {

  public OrdersJsonConverter(String jsonFilename) {
    super(jsonFilename);
  }
}
