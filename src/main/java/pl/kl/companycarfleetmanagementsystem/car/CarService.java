package pl.kl.companycarfleetmanagementsystem.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car createCar(CreateCarRequest request) {
        final Car car = Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .registrationNumber(request.getRegistrationNumber())
                .vinNumber(request.getVinNumber())
                .productionYear(request.getProductionYear())
                .build();

        return carRepository.save(car);
    }
  
      public List<Car> fetchAllCars() {

        return carRepository.findAll();
      }

    @Transactional
    public Car editCar(Long id, String brand, String model, String registrationNumber, String vinNumber, Integer productionYear) {
        final Car car = carRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car with id: " + id + "not found"));

        car.setBrand(brand);
        car.setModel(model);
        car.setRegistrationNumber(registrationNumber);
        car.setVinNumber(vinNumber);
        car.setProductionYear(productionYear);

        return carRepository.save(car);
    }

    public Car fetchCarById(Long carId) {

        return carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("Car with id: " + carId + " not found"));
    }
}
