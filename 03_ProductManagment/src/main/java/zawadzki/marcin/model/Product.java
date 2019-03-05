package zawadzki.marcin.model;

import lombok.*;
import zawadzki.marcin.enums.Category;
import zawadzki.marcin.exception.CustomProductException;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Product {

  private String name;
  private BigDecimal price;
  private Category category;

  public static ProductBuilder builder() {
    return new ProductBuilder();
  }

  private Product(ProductBuilder productBuilder) {
    this.name = productBuilder.name;
    this.price = productBuilder.price;
    this.category = productBuilder.category;
  }

  public static class ProductBuilder {

    private static final String nameRegex = "^[A-Z\\s]+$";

    private String name;
    private BigDecimal price;
    private Category category;

    public ProductBuilder name(String name) {
      try {
        if (!name.matches(nameRegex)) {
          throw new CustomProductException(
              "Product name must contains only capital letters or white spaces");
        }
        this.name = name;
        return this;
      } catch (Exception e) {
        throw new CustomProductException("Validation error for product name");
      }
    }

    public ProductBuilder price(BigDecimal price) {
      try {
        if (price == null) {
          throw new CustomProductException("Price cannot be null");
        }
        if (price.compareTo(ZERO) < 0) {
          throw new CustomProductException("Price cannot be smaller than 0");
        }
        this.price = price;
        return this;
      } catch (Exception e) {
        throw new CustomProductException("Validation error for price param");
      }
    }

    public ProductBuilder category(Category category) {
      try {
        if (Objects.isNull(category)) {
          throw new CustomProductException("Category cannot be null");
        }
        this.category = category;
        return this;
      } catch (Exception e) {
        throw new CustomProductException("Validation error for category type");
      }
    }

    public Product build() {
      return new Product(this);
    }
  }
}
