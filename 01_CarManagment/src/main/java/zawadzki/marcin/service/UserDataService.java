package zawadzki.marcin.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import zawadzki.marcin.model.CarColour;

class UserDataService {

  private Scanner sc = new Scanner(System.in);

  BigDecimal getBigDecimal() {
    System.out.println("Type price param (price greater than 0)");
    return sc.nextBigDecimal();
  }

  CarColour getCarColour() {
    System.out.println("Type colour param (Accepted colours from enum(BLACK, RED, GREEN, WHITE)");
    return CarColour.valueOf(sc.next());
  }

  Long getLong() {
    System.out.println("Type mileage param (Accepted greater than 0");
    return sc.nextLong();
  }

  String getString() {
    System.out.println("Type model param (only great letters and white spaces)");
    return sc.nextLine();
  }

  Set<String> getComponents() {
    System.out.println("Type components params (only great letters and white spaces)");
    Set<String> componentsList = new HashSet<>();
    while (sc.hasNextLine()) {

      componentsList.add(sc.next());
      if (sc.next().equals("##")) {
        break;
      }
    }
    return componentsList;
  }


  void close() {
    if (sc != null) {
      sc.close();
      sc = null;
    }
  }
}
