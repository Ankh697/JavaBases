package zawadzki.marcin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import zawadzki.marcin.enums.CarBodyColour;
import zawadzki.marcin.enums.CarBodyType;
import zawadzki.marcin.enums.EngineType;
import zawadzki.marcin.enums.WheelType;

class UserDataService {

  private Scanner sc = new Scanner(System.in);

  BigDecimal getBigDecimal() {
    System.out.println("Type price param (price greater than 0)");
    return sc.nextBigDecimal();
  }

  Integer getInt() {
    System.out.println("Size param for wheel");
    return Integer.parseInt(sc.next());
  }

  Double getPower() {
    System.out.println("Power param (price greater than 0)");
    return sc.nextDouble();
  }


  EngineType getEngineType() {
    System.out.println("Engine type param (Accepted colours from enum(DIESEL, GASOLINE, LPG)");
    return EngineType.valueOf(sc.next());
  }


  CarBodyColour getCarBodyColour() {
    System.out.println(
        "Car body colour param (Accepted colours from enum(BLACK,  SILVER,  WHITE,  RED,  BLUE,  GREEN)");
    return CarBodyColour.valueOf(sc.next());
  }

  CarBodyType getCarBodyType() {
    System.out.println("Car body type param (Accepted types from enum(SEDAN,  HATCHBACK,  COMBI)");
    return CarBodyType.valueOf(sc.next());
  }

  WheelType getWheelType() {
    System.out.println("Wheel type param (Accepted colours from enum(WINTER, SUMMER)");
    return WheelType.valueOf(sc.next());
  }

  String getWheelModel() {
    System.out.println("Type model for wheel param (only great letters and white spaces)");
    return sc.next();
  }

  Long getLong() {
    System.out.println("Type mileage param (Accepted greater than 0");
    return sc.nextLong();
  }

  String getString() {
    System.out.println("Type model param (only great letters and white spaces)");
    return sc.nextLine();
  }

  List<String> getComponentsAsList() {
    System.out.println("Type components params (only great letters and white spaces)");
    List<String> components = new ArrayList<>();
    while (sc.hasNextLine()) {
      System.out.println("You are adding components to list - if you finished type finish");
      components.add(sc.next());
      if (sc.next().equals("finish")) {
        break;
      }
    }
    return components;
  }

  void close() {
    if (sc != null) {
      sc.close();
      sc = null;
    }
  }
}
