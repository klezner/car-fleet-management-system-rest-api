package pl.kl.carfleetmanagementsystem.refueling;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.carfleetmanagementsystem.trip.Trip;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Refueling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @NotNull(message = "Trip is necessary")
    @ManyToOne
    private Trip trip;
}
