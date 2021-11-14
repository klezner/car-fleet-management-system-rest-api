package pl.kl.companycarfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.companycarfleetmanagementsystem.trip.TripMapper;

@Component
@RequiredArgsConstructor
public class RefuelingMapper {

    private final TripMapper tripMapper;

    public RefuelingResponse mapRefuelingToRefuelingResponse(Refueling refueling) {

        return RefuelingResponse.builder()
                .id(refueling.getId())
                .date(refueling.getDate())
                .meterStatus(refueling.getMeterStatus())
                .fuelAmount(refueling.getFuelAmount())
                .refuelingCost(refueling.getRefuelingCost())
                .trip(tripMapper.mapTripToTripResponse(refueling.getTrip()))
                .build();
    }
}
