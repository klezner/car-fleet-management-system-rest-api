package pl.kl.companycarfleetmanagementsystem.car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarTestHelper {

    static Car provideCarNr2() {
        final Car car = Car.builder()
                .brand("Citroen")
                .model("DS4")
                .registrationNumber("WX5974A")
                .productionYear(2016)
                .vinNumber("VF7NXAHRMFY570817")
                .build();

        return car;
    }

    static Car provideCarNr1() {
        final Car car = Car.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();

        return car;
    }

    public static List<Car> provideCarList() {
        final List<Car> cars = new ArrayList<>(Arrays.asList(
                provideCarNr1(),
                provideCarNr2()
        ));

        return cars;
    }
}
