package pl.kl.companycarfleetmanagementsystem.car;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import pl.kl.companycarfleetmanagementsystem.fleetcard.FleetCard;
import pl.kl.companycarfleetmanagementsystem.trip.Trip;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Car brand cannot be blank")
    private String brand;
    @NotBlank(message = "Car model cannot be blank")
    private String model;
    @NotBlank(message = "Car registration number cannot be blank")
    @Length(min = 3, max = 7, message = "Incorrect car registration number length")
    private String registrationNumber;
    @NotBlank(message = "Car vin number cannot be blank")
    @Length(min = 17, max = 17, message = "Incorrect car vin number length")
    private String vinNumber;
    @NotNull(message = "Car production year cannot be blank")
    @Min(2000)
    @Max(2050)
    private Integer productionYear;
    @OneToMany(mappedBy = "car")
    private Set<Trip> trips;
    @OneToOne(mappedBy = "car")
    private FleetCard fleetCard;
}
