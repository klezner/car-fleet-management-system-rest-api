package pl.kl.carfleetmanagementsystem.car;

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
    public Car editCar(UpdateCarRequest request) {
        final Car car = carRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Car with id: " + request.getId() + " not found"));

        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setRegistrationNumber(request.getRegistrationNumber());
        car.setVinNumber(request.getVinNumber());
        car.setProductionYear(request.getProductionYear());

        return carRepository.save(car);
    }

    public Car fetchCarById(Long id) {

        return carRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car with id: " + id + " not found"));
    }
}
