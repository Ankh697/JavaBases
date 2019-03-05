package zawadzki.marcin.model;

import com.google.common.primitives.UnsignedInteger;
import lombok.*;
import zawadzki.marcin.exception.CustomOrderException;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Order {
  private Customer customer;
  private Product product;

  private UnsignedInteger quantity;
  private LocalDate estimatedRealizationDate;

  public static OrderBuilder builder() {
    return new OrderBuilder();
  }

  private Order(OrderBuilder orderBuilder) {
    this.customer = orderBuilder.customer;
    this.product = orderBuilder.product;
    this.quantity = orderBuilder.quantity;
    this.estimatedRealizationDate = orderBuilder.estimatedRealizationDate;
  }

  public static class OrderBuilder {

    private Customer customer;
    private Product product;
    private UnsignedInteger quantity;
    private LocalDate estimatedRealizationDate;

    public OrderBuilder quantity(UnsignedInteger quantity) {
      try {
        if (Objects.isNull(quantity)) {
          throw new CustomOrderException("Quantity cannot be null");
        }
        if (quantity.compareTo(UnsignedInteger.ZERO) < 0) {
          throw new CustomOrderException("Quantity cannot be smaller than 18");
        }
        this.quantity = quantity;
        return this;
      } catch (Exception e) {
        throw new CustomOrderException("Validation error for quantity");
      }
    }

    public OrderBuilder customer(Customer customer) {
      try {
        if (Objects.isNull(customer)) {
          throw new CustomOrderException("Customer cannot be null");
        }
        this.customer = customer;
        return this;
      } catch (Exception e) {
        throw new CustomOrderException("Validation error for customer");
      }
    }

    public OrderBuilder product(Product product) {
      try {
        if (Objects.isNull(product)) {
          throw new CustomOrderException("Product cannot be null");
        }
        this.product = product;
        return this;
      } catch (Exception e) {
        throw new CustomOrderException("Validation error for product");
      }
    }

    public OrderBuilder estimatedRealizationDate(LocalDate date) {
      LocalDate currentDate = LocalDate.now();
      try {
        if (date.isBefore(currentDate)) {
          throw new CustomOrderException("Date cannot be in the past");
        }
        this.estimatedRealizationDate = date;
        return this;
      } catch (Exception e) {
        throw new CustomOrderException("Validation error for estimated realization date of order");
      }
    }

    public Order build() {
      return new Order(this);
    }
  }
}
