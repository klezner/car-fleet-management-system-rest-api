package pl.kl.carfleetmanagementsystem.car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarTestHelper {

    static Car provideCar2() {

        return Car.builder()
                .id(2222L)
                .brand(CreateCarRequestTestHelper.provideCreateCar2Request().getBrand())
                .model(CreateCarRequestTestHelper.provideCreateCar2Request().getModel())
                .registrationNumber(CreateCarRequestTestHelper.provideCreateCar2Request().getRegistrationNumber())
                .productionYear(CreateCarRequestTestHelper.provideCreateCar2Request().getProductionYear())
                .vinNumber(CreateCarRequestTestHelper.provideCreateCar2Request().getVinNumber())
                .build();
    }

    static Car provideCar1() {

        return Car.builder()
                .id(1111L)
                .brand(CreateCarRequestTestHelper.provideCreateCar1Request().getBrand())
                .model(CreateCarRequestTestHelper.provideCreateCar1Request().getModel())
                .registrationNumber(CreateCarRequestTestHelper.provideCreateCar1Request().getRegistrationNumber())
                .productionYear(CreateCarRequestTestHelper.provideCreateCar1Request().getProductionYear())
                .vinNumber(CreateCarRequestTestHelper.provideCreateCar1Request().getVinNumber())
                .build();
    }

    public static List<Car> provideCarList() {

        return new ArrayList<>(Arrays.asList(
                provideCar1(),
                provideCar2()
        ));
    }
}
