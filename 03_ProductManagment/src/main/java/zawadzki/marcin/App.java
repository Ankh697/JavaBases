package zawadzki.marcin;

import zawadzki.marcin.converters.OrdersJsonConverter;
import zawadzki.marcin.enums.Category;
import zawadzki.marcin.model.Customer;
import zawadzki.marcin.model.Order;
import zawadzki.marcin.model.Orders;
import zawadzki.marcin.model.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;

public class App {
  public static void main(String[] args) throws IOException {
    // new Product("LEVER", BigDecimal.valueOf(120), Category.C)
    Orders orderList =
        new Orders(
            newArrayList(
                Order.builder()
                    .product(
                        Product.builder()
                            .category(Category.B)
                            .name("LEVER")
                            .price(BigDecimal.valueOf(120))
                            .build())
                    .customer(
                        Customer.builder()
                            .name("JACK")
                            .surname("MULLER")
                            .birthDate(LocalDate.of(1994, 7, 5))
                            .email("jackmuller@gmail.com")
                            .build())
                    .estimatedRealizationDate(LocalDate.now().plusDays(2))
                    .quantity(5)
                    .build(),
                Order.builder()
                    .product(new Product("HOOK", BigDecimal.valueOf(700), Category.B))
                    .customer(
                        new Customer(
                            "ADAM", "NEUMANN", LocalDate.of(1980, 5, 2), "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(6))
                    .quantity(3)
                    .build(),
                Order.builder()
                    .product(new Product("TURBINE", BigDecimal.valueOf(550), Category.A))
                    .customer(
                        new Customer(
                            "ROBERT", "KASTNER", LocalDate.of(1980, 4, 1), "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(3))
                    .quantity(2)
                    .build(),
                Order.builder()
                    .product(new Product("ALTERNATOR", BigDecimal.valueOf(1200), Category.B))
                    .customer(
                        new Customer(
                            "CONNOR", "EBELING", LocalDate.of(1980, 6, 12), "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(2))
                    .quantity(12)
                    .build(),
                Order.builder()
                    .product(new Product("LEVER", BigDecimal.valueOf(233), Category.A))
                    .customer(
                        new Customer(
                            "HANS",
                            "ZIMMERMANN",
                            LocalDate.of(1980, 7, 10),
                            "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(4))
                    .quantity(11)
                    .build(),
                Order.builder()
                    .product(new Product("ENGINE", BigDecimal.valueOf(454), Category.C))
                    .customer(
                        new Customer(
                            "JONAS", "WITT", LocalDate.of(1980, 8, 14), "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(8))
                    .quantity(10)
                    .build(),
                Order.builder()
                    .product(new Product("HOOD", BigDecimal.valueOf(450), Category.B))
                    .customer(
                        new Customer(
                            "MARK", "SZULC", LocalDate.of(1980, 5, 8), "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(10))
                    .quantity(20)
                    .build(),
                Order.builder()
                    .product(new Product("HEADLIGHTS", BigDecimal.valueOf(750), Category.B))
                    .customer(
                        new Customer(
                            "SEBASTIAN",
                            "NEUMAYER",
                            LocalDate.of(1980, 4, 12),
                            "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(12))
                    .quantity(10)
                    .build(),
                Order.builder()
                    .product(new Product("BUMPER", BigDecimal.valueOf(1000), Category.A))
                    .customer(
                        new Customer(
                            "TOM", "BAUSCH", LocalDate.of(1980, 10, 7), "jackmuller@gmail.com"))
                    .estimatedRealizationDate(LocalDate.now().plusDays(2))
                    .quantity(4)
                    .build()));

    final String carsStoreJsonFilename = "orders.json";
    OrdersJsonConverter carsJsonConverter = new OrdersJsonConverter(carsStoreJsonFilename);
    carsJsonConverter.toJson(orderList);

    System.out.println("Average price for product collection between two dates: ");
    System.out.println(
        orderList.averagePriceForAllOrdersBetweenTwoDates(
            LocalDate.now(), LocalDate.now().plusDays(10)));

    System.out.println("Min amount of orders -> date");
    System.out.println(orderList.findDateWithLeastNumbersOfOrders());

    System.out.println("Max amount of orders -> date");
    System.out.println(orderList.findDateWithTheMostNumbersOfOrders());

    System.out.println("MAX PRICE");
    System.out.println(orderList.findCustomerWithTheHighestPriceForOrder());

    System.out.println("You can find customer with product quantity greater than in CustomerWithOrderQuantityGreater.json file");
    orderList.customersWhoOrderedAtLeastNumberOfProducts(19);

    System.out.println("Number of customer with orders number greater than: ");
    System.out.println(orderList.customerNumberWhoOrderedAtLeastNumberOfProducts(10));

    System.out.println("The most common product category");
    System.out.println(orderList.theMostCommonProductCategory());

    System.out.println("Customer with the highest price for order");
    System.out.println(orderList.findCustomerWithTheHighestPriceForOrder());

    System.out.println("Month with number of ordered products");
    System.out.println(orderList.monthWithTheNumberOfOrderedProducts());

    System.out.println(orderList.method1());

    System.out.println(Orders.stringLengthForEachWord());

    System.out.println(orderList.totalPriceOfAllOrdersAfterPriceReduction());
  }
}
