package pl.kl.companycarfleetmanagementsystem.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car createCar(String brand, String model, String registrationNumber, String vinNumber, Integer productionYear) {
        final Car car = Car.builder()
                .brand(brand)
                .model(model)
                .registrationNumber(registrationNumber)
                .vinNumber(vinNumber)
                .productionYear(productionYear)
                .build();

        return carRepository.save(car);
    }
}
