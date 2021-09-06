package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.car.Car;
import pl.kl.companycarfleetmanagementsystem.car.CarService;
import pl.kl.companycarfleetmanagementsystem.validator.MeterStatusValidator;
import pl.kl.companycarfleetmanagementsystem.validator.TripDateValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final CarService carService;
    private final TripRepository tripRepository;

    public Trip createTrip(CreateTripRequest request) {

        TripDateValidator.validateTripDate(request.getDepartureDate(), request.getReturnDate());
        MeterStatusValidator.validateMeterStatus(request.getDepartureMeterStatus(), request.getReturnMeterStatus());

        final Car car = carService.fetchCarById(request.getCarId());

        final Trip trip = Trip.builder()
                .departureDate(request.getDepartureDate())
                .returnDate(request.getReturnDate())
                .departureMeterStatus(request.getDepartureMeterStatus())
                .returnMeterStatus(request.getReturnMeterStatus())
                .comments(request.getComments())
                .car(car)
                .build();

        return tripRepository.save(trip);
    }

    public List<Trip> fetchAllTrips() {

        return tripRepository.findAll();
    }
}
