package zawadzki.marcin.service;

import java.util.Scanner;

public class UserDataService {
    private Scanner sc = new Scanner(System.in);

    public int getInt(String message) {
        System.out.println(message);
        return Integer.parseInt(sc.nextLine());
    }

    public void close() {
        if (sc != null) {
            sc.close();
            sc = null;
        }
    }
}
