package pl.kl.companycarfleetmanagementsystem.car;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Car Controller")
public class CarController {

    private final CarMapper carMapper;
    private final CarService carService;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new car", notes = "Allows you to add a new car in the form of a json request")
    public ResponseEntity<CarResponse> addCar(@RequestBody @Valid CreateCarRequest request) {
        final Car car = carService.createCar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carMapper.mapCarToCarResponse(car));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all cars", notes = "Allows you to get a list of all cars")
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
                            .map(carMapper::mapCarToCarResponse)
                            .collect(Collectors.toList()));
        }
    }

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update car", notes = "Allows you to update a car in the form of a json request")
    public ResponseEntity<CarResponse> updateCar(@RequestBody @Valid UpdateCarRequest request) {
        final Car car = carService.editCar(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carMapper.mapCarToCarResponse(car));
    }
}
