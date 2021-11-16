package pl.kl.carfleetmanagementsystem.car;

import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarResponse mapCarToCarResponse(Car car) {

        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .registrationNumber(car.getRegistrationNumber())
                .vinNumber(car.getVinNumber())
                .productionYear(car.getProductionYear())
                .build();
    }
}
