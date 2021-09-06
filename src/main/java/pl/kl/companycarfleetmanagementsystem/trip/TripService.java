package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.car.Car;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;


    public Trip createTrip(LocalDate departureDate, LocalDate returnDate, Integer departureMeterStatus, Integer returnMeterStatus, String comments, Car car) {
        final Trip trip = Trip.builder()
                .departureDate(departureDate)
                .returnDate(returnDate)
                .departureMeterStatus(departureMeterStatus)
                .returnMeterStatus(returnMeterStatus)
                .comments(comments)
                .car(car)
                .build();

        return tripRepository.save(trip);
    }
}
