package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateEmployeeRequest {

    @NotNull(message = "Employee id is mandatory")
    private Long id;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "First name cannot be blank")
    private String lastName;
}
