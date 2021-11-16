package pl.kl.carfleetmanagementsystem.refueling;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.trip.TripResponse;

import java.time.LocalDate;

@Getter
@Builder
public class RefuelingResponse {

    private Long id;
    private LocalDate date;
    private Integer meterStatus;
    private Double fuelAmount;
    private Double refuelingCost;
    private TripResponse trip;
}
