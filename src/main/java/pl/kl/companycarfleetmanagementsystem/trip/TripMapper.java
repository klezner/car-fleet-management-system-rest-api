package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.companycarfleetmanagementsystem.car.CarMapper;

@Component
@RequiredArgsConstructor
public class TripMapper {

    private final CarMapper carMapper;

    public TripResponse mapTripToTripResponse(Trip trip) {

        return TripResponse.builder()
                .id(trip.getId())
                .departureDate(trip.getDepartureDate())
                .returnDate(trip.getReturnDate())
                .departureMeterStatus(trip.getDepartureMeterStatus())
                .returnMeterStatus(trip.getReturnMeterStatus())
                .comments(trip.getComments())
                .car(carMapper.mapCarToCarResponse(trip.getCar()))
                .build();
    }
}
