package zawadzki.marcin.service;

import zawadzki.marcin.exception.CustomException;

public class MenuService {
    private final String jsonFIlename = "";
    private final CarsService carsService = new CarsService(jsonFIlename);
    private final UserDataService userDataService = new UserDataService();

    // dodatowo w main menu masz opcje:
    // 1. dodawanie nowego samochodu
    // 2. powrot do danych z pliku JSON do stanu poczatkowego
    public void mainMenu() {
        while (true) {
            try {

                int option = 1;
                switch(option) {
                    case 7:
                        userDataService.close();
                        return;
                }

            } catch (CustomException e) {
                //. ...
            }
        }
    }

    // ....
}
