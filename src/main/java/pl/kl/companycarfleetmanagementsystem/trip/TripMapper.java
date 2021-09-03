package pl.kl.companycarfleetmanagementsystem.trip;

import org.springframework.stereotype.Component;

@Component
public class TripMapper {
    public TripResponse mapTripToTripResponse(Trip trip) {

        return TripResponse.builder()
                .id(trip.getId())
                .departureDate(trip.getDepartureDate())
                .returnDate(trip.getReturnDate())
                .departureMeterStatus(trip.getDepartureMeterStatus())
                .returnMeterStatus(trip.getReturnMeterStatus())
                .comments(trip.getComments())
                .build();
    }
}
