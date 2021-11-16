package pl.kl.carfleetmanagementsystem.department;

import lombok.*;
import pl.kl.carfleetmanagementsystem.company.Company;
import pl.kl.carfleetmanagementsystem.employee.Employee;

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
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Department name cannot be blank")
    private String name;
    @NotBlank(message = "Department abbreviation cannot be blank")
    private String abbreviation;
    private String comment;
    @NotNull(message = "Company is necessary")
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;
}
