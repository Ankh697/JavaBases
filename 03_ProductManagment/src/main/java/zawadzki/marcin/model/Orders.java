package zawadzki.marcin.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zawadzki.marcin.converters.OrdersJsonConverter;

import java.util.List;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class Orders {

  private final List<Product> productList;

  Orders(String jsonFilename) {
    jsonFilename = "D:\\Programowanie\\JavaBases\\orders.json";
    this.productList = new OrdersJsonConverter(jsonFilename).fromJson().get().getProductList();
  }
}
