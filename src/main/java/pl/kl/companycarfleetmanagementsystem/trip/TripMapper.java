package pl.kl.companycarfleetmanagementsystem.trip;

import org.springframework.stereotype.Component;
import pl.kl.companycarfleetmanagementsystem.car.CarResponse;

@Component
public class TripMapper {
    public TripResponse mapTripToTripResponse(Trip trip, CarResponse carResponse) {

        return TripResponse.builder()
                .id(trip.getId())
                .departureDate(trip.getDepartureDate())
                .returnDate(trip.getReturnDate())
                .departureMeterStatus(trip.getDepartureMeterStatus())
                .returnMeterStatus(trip.getReturnMeterStatus())
                .comments(trip.getComments())
                .car(carResponse)
                .build();
    }
}
