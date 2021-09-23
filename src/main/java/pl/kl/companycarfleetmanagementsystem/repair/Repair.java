package pl.kl.companycarfleetmanagementsystem.repair;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.companycarfleetmanagementsystem.trip.Trip;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Left date at the workshop cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leftDate;
    @NotNull(message = "Left meter status at workshop cannot be blank")
    private Integer leftMeterStatus;
    @NotBlank(message = "Repair invoice number cannot be blank")
    private String invoiceNumber;
    @NotNull(message = "Repair invoice dater cannot be blank")
    private LocalDate invoiceDate;
    @NotNull(message = "Repair cost cannot be blank")
    private Double repairCost;
    @NotNull(message = "Pickup date at the workshop date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;
    @NotNull(message = "Trip is necessary")
    @ManyToOne
    private Trip trip;
}
