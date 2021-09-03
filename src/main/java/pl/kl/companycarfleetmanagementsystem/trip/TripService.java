package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;


    public Trip createTrip(LocalDate departureDate, LocalDate returnDate, Integer departureMeterStatus, Integer returnMeterStatus, String comments) {
        final Trip trip = Trip.builder()
                .departureDate(departureDate)
                .returnDate(returnDate)
                .departureMeterStatus(departureMeterStatus)
                .returnMeterStatus(returnMeterStatus)
                .comments(comments)
                .build();

        return tripRepository.save(trip);
    }
}
