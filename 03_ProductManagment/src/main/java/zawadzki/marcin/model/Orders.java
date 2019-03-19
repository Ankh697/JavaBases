package zawadzki.marcin.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zawadzki.marcin.converters.OrdersJsonConverter;
import zawadzki.marcin.enums.Category;
import zawadzki.marcin.exception.CustomOrderException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.time.Period.between;
import static java.util.Arrays.asList;
import static java.util.Objects.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class Orders {

  private final List<Order> productList;

  private static final int MAXIMAL_AGE_WITH_DISCOUNT = 25;
  private static final BigDecimal DISCOUNT_RATIO_FOR_CUSTOMER_YOUNGER_THAN_25 = valueOf(0.97);
  private static final BigDecimal DISCOUNT_RATIO_FOR_DELIVERY_DATE_SHORTER_THAN_2 = valueOf(0.98);
  private static final int MAXIMAL_DATES_NUMBER_FOR_DISCOUNT = 2;

  Orders() {
    String jsonFilename = "D:\\Programowanie\\JavaBases\\orders.json";
    this.productList = requireNonNull(new OrdersJsonConverter(jsonFilename).fromJson().orElse(null)).getProductList();
  }

  public BigDecimal averagePriceForAllOrdersBetweenTwoDates(LocalDate begin, LocalDate end) {
    return productList.stream()
        .filter(
            order ->
                order.getEstimatedRealizationDate().compareTo(begin) > 0
                    && order.getEstimatedRealizationDate().compareTo(end) < 0)
        .map(order -> order.getProduct().getPrice())
        .reduce(ZERO, BigDecimal::add)
        .divide(BigDecimal.valueOf(productList.size()), BigDecimal.ROUND_HALF_UP);
  }

  public LocalDate findDateWithTheMostNumbersOfOrders() {
    return productList.stream()
        .max(Comparator.comparing(Order::getQuantity))
        .map(Order::getEstimatedRealizationDate)
        .orElse(LocalDate.of(1970, 1, 1));
  }

  public LocalDate findDateWithLeastNumbersOfOrders() {
    return productList.stream()
        .min(Comparator.comparing(Order::getQuantity))
        .map(Order::getEstimatedRealizationDate)
        .orElse(LocalDate.of(1970, 1, 1));
  }

  public Customer findCustomerWithTheHighestPriceForOrder() {

    return requireNonNull(
            productList.stream()
                .collect(
                    Collectors.toMap(
                        Order::getCustomer,
                        order ->
                            order
                                .getProduct()
                                .getPrice()
                                .multiply(new BigDecimal(String.valueOf(order.getQuantity())))))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null))
        .getKey();
  }

  public void customersWhoOrderedAtLeastNumberOfProducts(int x) throws IOException {
    List<Customer> collection =
        productList.stream()
            .filter(p -> p.getQuantity() > x)
            .map(Order::getCustomer)
            .collect(Collectors.toList());

    try (Writer writer = new FileWriter("CustomerWithOrderQuantityGreater.json")) {
      Gson gson = new GsonBuilder().create();
      gson.toJson(collection, writer);
    }
  }

  public long customerNumberWhoOrderedAtLeastNumberOfProducts(int x) {
    return productList.stream().filter(p -> p.getQuantity() > x).map(Order::getCustomer).count();
  }

  public Category theMostCommonProductCategory() {
    return productList.stream()
        .collect(groupingBy(order -> order.getProduct().getCategory(), counting()))
        .entrySet()
        .stream()
        .max(Comparator.comparing(Map.Entry::getValue))
        .map(Map.Entry::getKey)
        .orElse(null);
  }

  public Map<Month, Integer> monthWithTheNumberOfOrderedProducts() {
    return productList.stream()
        .collect(
            groupingBy(
                order -> order.getEstimatedRealizationDate().getMonth(),
                Collectors.summingInt(Order::getQuantity)));
  }

  public Map<Month, Category> method1() {

    return productList.stream()
        .collect(Collectors.groupingBy(o -> o.getEstimatedRealizationDate().getMonth()))
        .entrySet()
        .stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                e ->
                    e.getValue().stream()
                        .collect(
                            Collectors.groupingBy(
                                o -> o.getProduct().getCategory(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .min((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                        .orElseThrow(() -> new CustomOrderException("Category Exception"))
                        .getKey()));
  }

  public static Map<Integer, List<String>> stringLengthForEachWord() {

    List<String> strings = asList("Bogdan", "Zbyszek", "Paweł", "Marcin", "Patryk");

    return strings.stream()
        .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
    //  return strings.stream().collect(Collectors.groupingBy(String::length));
  }

  public BigDecimal totalPriceOfAllOrdersAfterPriceReduction() {
    return productList.stream()
        .map(
            i -> {
              if (between(i.getCustomer().getBirthDate(), LocalDate.now()).getYears()
                  < MAXIMAL_AGE_WITH_DISCOUNT) {
                return i.getProduct()
                    .getPrice()
                    .multiply(DISCOUNT_RATIO_FOR_CUSTOMER_YOUNGER_THAN_25)
                    .multiply(valueOf(i.getQuantity()));
              } else {
                if (between(i.getEstimatedRealizationDate(), LocalDate.now()).getDays()
                    < MAXIMAL_DATES_NUMBER_FOR_DISCOUNT) {
                  return i.getProduct()
                      .getPrice()
                      .multiply(DISCOUNT_RATIO_FOR_DELIVERY_DATE_SHORTER_THAN_2)
                      .multiply(valueOf(i.getQuantity()));
                }
              }
              return i.getProduct().getPrice();
            })
        .reduce(ZERO, BigDecimal::add);
  }
}
