package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.Builder;
import lombok.Getter;
import pl.kl.companycarfleetmanagementsystem.department.DepartmentResponse;

@Getter
@Builder
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private DepartmentResponse department;
}
