package pl.kl.carfleetmanagementsystem.employee;

import lombok.Builder;
import lombok.Getter;
import pl.kl.carfleetmanagementsystem.department.DepartmentResponse;

@Getter
@Builder
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private DepartmentResponse department;
}
