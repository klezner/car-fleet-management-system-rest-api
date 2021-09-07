package pl.kl.companycarfleetmanagementsystem.car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarTestHelper {

    static Car provideCar2() {

        return Car.builder()
                .id(2222L)
                .brand("Citroen")
                .model("DS4")
                .registrationNumber("WX5974A")
                .productionYear(2016)
                .vinNumber("VF7NXAHRMFY570817")
                .build();
    }

    static Car provideCar1() {

        return Car.builder()
                .id(1111L)
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    public static List<Car> provideCarList() {

        return new ArrayList<>(Arrays.asList(
                provideCar1(),
                provideCar2()
        ));
    }
}
