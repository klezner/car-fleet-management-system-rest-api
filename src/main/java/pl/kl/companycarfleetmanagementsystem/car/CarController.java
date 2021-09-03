package pl.kl.companycarfleetmanagementsystem.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
