package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.car.Car;
import pl.kl.companycarfleetmanagementsystem.car.CarRepository;
import pl.kl.companycarfleetmanagementsystem.car.CarService;
import pl.kl.companycarfleetmanagementsystem.validator.MeterStatusValidator;
import pl.kl.companycarfleetmanagementsystem.validator.TripDateValidator;

import java.util.NoSuchElementException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final CarRepository carRepository;
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

    public Trip editTrip(UpdateTripRequest request) {
        final Trip trip = tripRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Trip with id: " + request.getId() + "not found"));

        final Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new NoSuchElementException("Car with id: " + request.getCarId() + "not found"));

        trip.setDepartureDate(request.getDepartureDate());
        trip.setReturnDate(request.getReturnDate());
        trip.setDepartureMeterStatus(request.getDepartureMeterStatus());
        trip.setReturnMeterStatus(request.getReturnMeterStatus());
        trip.setComments(request.getComments());
        trip.setCar(car);

        return tripRepository.save(trip);
    }
}
