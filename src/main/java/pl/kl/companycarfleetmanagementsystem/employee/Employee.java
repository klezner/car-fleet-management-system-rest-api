package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.*;
import pl.kl.companycarfleetmanagementsystem.department.Department;
import pl.kl.companycarfleetmanagementsystem.trip.Trip;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @OneToMany(mappedBy = "employee")
    private Set<Trip> trips;
    @NotNull(message = "Department is necessary")
    @ManyToOne
    private Department department;
}
