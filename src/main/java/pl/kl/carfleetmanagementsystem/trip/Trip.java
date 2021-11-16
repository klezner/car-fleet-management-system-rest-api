package pl.kl.carfleetmanagementsystem.trip;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kl.carfleetmanagementsystem.car.Car;
import pl.kl.carfleetmanagementsystem.employee.Employee;
import pl.kl.carfleetmanagementsystem.refueling.Refueling;
import pl.kl.carfleetmanagementsystem.repair.Repair;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Departure date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @NotNull(message = "Departure date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    @NotNull(message = "Meter status cannot be blank")
    @Min(0)
    private Integer departureMeterStatus;
    @NotNull(message = "Meter status cannot be blank")
    @Min(0)
    private Integer returnMeterStatus;
    private String comments;
    @NotNull(message = "Car is necessary")
    @ManyToOne
    private Car car;
    @OneToMany(mappedBy = "trip")
    private Set<Refueling> refuelings;
    @NotNull(message = "Employee is necessary")
    @ManyToOne
    private Employee employee;
    @OneToMany(mappedBy = "trip")
    private Set<Repair> repairs;
}
