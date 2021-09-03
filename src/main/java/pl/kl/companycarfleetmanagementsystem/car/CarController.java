package pl.kl.companycarfleetmanagementsystem.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/car")
public class CarController {

    private final CarMapper carMapper;
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> addCar(@RequestBody @Valid CreateCarRequest request) {
        final Car car = carService.createCar(
                request.getBrand(),
                request.getModel(),
                request.getRegistrationNumber(),
                request.getVinNumber(),
                request.getProductionYear()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carMapper.mapCarToCarResponse(car));
    }

      @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        final List<Car> cars = carService.fetchAllCars();

        if (cars.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cars.stream()
                            .map(car -> carMapper.mapCarToCarResponse(car))
                            .collect(Collectors.toList()));
        }
    }

    @PutMapping
    public ResponseEntity<CarResponse> updateCar(@RequestBody @Valid UpdateCarRequest request) {
        final Car car = carService.editCar(
                request.getId(),
                request.getBrand(),
                request.getModel(),
                request.getRegistrationNumber(),
                request.getVinNumber(),
                request.getProductionYear()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carMapper.mapCarToCarResponse(car));
    }
}
