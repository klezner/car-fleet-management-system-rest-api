package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kl.companycarfleetmanagementsystem.car.Car;
import pl.kl.companycarfleetmanagementsystem.car.CarMapper;
import pl.kl.companycarfleetmanagementsystem.car.CarService;
import pl.kl.companycarfleetmanagementsystem.validator.MeterStatusValidator;
import pl.kl.companycarfleetmanagementsystem.validator.TripDateValidator;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/trip")
public class TripController {

    private final CarMapper carMapper;
    private final CarService carService;
    private final TripMapper tripMapper;
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponse> addTrip(@RequestBody @Valid CreateTripRequest request) {

        TripDateValidator.validateTripDate(request.getDepartureDate(), request.getReturnDate());
        MeterStatusValidator.validateMeterStatus(request.getDepartureMeterStatus(), request.getReturnMeterStatus());

        final Car car = carService.fetchCarById(request.getCarId());

        final Trip trip = tripService.createTrip(
                request.getDepartureDate(),
                request.getReturnDate(),
                request.getDepartureMeterStatus(),
                request.getReturnMeterStatus(),
                request.getComments(),
                car
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripMapper.mapTripToTripResponse(trip, carMapper.mapCarToCarResponse(car)));
    }
}
