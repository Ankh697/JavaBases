package zawadzki.marcin;

import com.google.common.collect.Lists;
import zawadzki.marcin.converters.OrdersJsonConverter;
import zawadzki.marcin.enums.Category;
import zawadzki.marcin.model.Orders;
import zawadzki.marcin.model.Product;

import java.math.BigDecimal;

public class App {
  public static void main(String[] args) {

    Orders orders =
        new Orders(
            Lists.newArrayList(
                Product.builder()
                    .name("LEVER")
                    .price(BigDecimal.valueOf(3213))
                    .category(Category.B)
                    .build(),
                Product.builder()
                    .name("HOOK")
                    .price(BigDecimal.valueOf(3213))
                    .category(Category.C)
                    .build(),
                Product.builder()
                    .name("ALTERNATOR")
                    .price(BigDecimal.valueOf(3213))
                    .category(Category.A)
                    .build(),
                Product.builder()
                    .name("PIN")
                    .price(BigDecimal.valueOf(3213))
                    .category(Category.B)
                    .build()));
    final String carsStoreJsonFilename = "orders.json";
    OrdersJsonConverter carsJsonConverter = new OrdersJsonConverter(carsStoreJsonFilename);
    carsJsonConverter.toJson(orders);
  }
}
