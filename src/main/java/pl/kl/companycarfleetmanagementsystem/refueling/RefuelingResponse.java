package pl.kl.companycarfleetmanagementsystem.refueling;

import lombok.Builder;
import lombok.Getter;
import pl.kl.companycarfleetmanagementsystem.trip.TripResponse;

import java.time.LocalDate;

@Getter
@Builder
public class RefuelingResponse {

    private Long id;
    private LocalDate date;
    private Integer meterStatus;
    private TripResponse trip;
}
