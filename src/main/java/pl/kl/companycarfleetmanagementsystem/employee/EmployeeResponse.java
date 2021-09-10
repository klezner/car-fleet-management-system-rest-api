package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
}
