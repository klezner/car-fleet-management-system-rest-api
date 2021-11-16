package pl.kl.carfleetmanagementsystem.company;

import lombok.*;
import pl.kl.carfleetmanagementsystem.department.Department;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    @OneToMany(mappedBy = "company")
    private Set<Department> departments;
}
