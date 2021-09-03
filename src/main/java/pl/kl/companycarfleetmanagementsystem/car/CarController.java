package pl.kl.companycarfleetmanagementsystem.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
