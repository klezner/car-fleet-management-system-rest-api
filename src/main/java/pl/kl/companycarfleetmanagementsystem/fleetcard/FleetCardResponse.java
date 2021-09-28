package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.Builder;
import lombok.Getter;
import pl.kl.companycarfleetmanagementsystem.car.CarResponse;

import java.time.LocalDate;

@Getter
@Builder
public class FleetCardResponse {

    private Long id;
    private String number;
    private LocalDate expirationDate;
    private String type;
    private CarResponse car;
}
