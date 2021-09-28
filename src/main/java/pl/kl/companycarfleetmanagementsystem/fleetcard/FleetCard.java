package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.companycarfleetmanagementsystem.car.Car;

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
public class FleetCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Fleet card number cannot be blank")
    private String number;
    @NotNull(message = "Fleet card expiration date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    @NotBlank(message = "Fleet card type cannot be blank")
    private String type;
    @NotNull(message = "Car is necessary")
    @OneToOne
    private Car car;
}
