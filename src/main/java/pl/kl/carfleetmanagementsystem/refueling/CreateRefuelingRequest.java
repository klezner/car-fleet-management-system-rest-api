package pl.kl.carfleetmanagementsystem.refueling;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class CreateRefuelingRequest {

    @NotNull(message = "Refueling date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "Meter status during refueling cannot be blank")
    private Integer meterStatus;
    @NotNull(message = "Amount of fuel refueled cannot be blank")
    @Min(0)
    private Double fuelAmount;
    @NotNull(message = "Refueling cost cannot be blank")
    @Min(0)
    private Double refuelingCost;
    @NotNull(message = "Trip id cannot be blank")
    private Long tripId;
}
