package zawadzki.marcin.model;

import lombok.*;
import org.apache.commons.validator.routines.EmailValidator;
import zawadzki.marcin.exception.CustomCustomerException;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Customer {

  private String name;
  private String surname;
  private Long age;
  private String email;

  public static CustomerBuilder builder() {
    return new CustomerBuilder();
  }

  private Customer(CustomerBuilder customerBuilder) {
    this.name = customerBuilder.name;
    this.surname = customerBuilder.surname;
    this.age = customerBuilder.age;
    this.email = customerBuilder.email;
  }

  public static class CustomerBuilder {

    private static final String nameRegex = "^[A-Z\\s]+$";

    private String name;
    private String surname;
    private Long age;
    private String email;

    public CustomerBuilder name(String name) {
      try {
        if (!name.matches(nameRegex)) {
          throw new CustomCustomerException(
              "Customer name must contains only capital letters or white spaces");
        }
        this.name = name;
        return this;
      } catch (Exception e) {
        throw new CustomCustomerException("Validation error for customer name ");
      }
    }

    public CustomerBuilder surname(String surname) {
      try {
        if (!surname.matches(nameRegex)) {
          throw new CustomCustomerException(
              "Customer surname must contains only capital letters or white spaces");
        }
        this.surname = surname;
        return this;
      } catch (Exception e) {
        throw new CustomCustomerException("Validation error for customer surname ");
      }
    }

    public CustomerBuilder age(Long age) {
      try {
        if (Objects.isNull(age)) {
          throw new CustomCustomerException("Age cannot be null");
        }
        if (age <= 18) {
          throw new CustomCustomerException("Age cannot be smaller than 18");
        }
        this.age = age;
        return this;
      } catch (Exception e) {
        throw new CustomCustomerException("Validation error for age");
      }
    }

    public CustomerBuilder email(String email) {
      try {
        if (Objects.isNull(email)) {
          throw new CustomCustomerException("Email address cannot be null");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
          throw new CustomCustomerException("Wrong patter for customer email value");
        }
        this.email = email;
        return this;
      } catch (Exception e) {
        throw new CustomCustomerException("Validation error for customer email");
      }
    }

    public Customer build() {
      return new Customer(this);
    }
  }
}
