package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateEmployeeRequest {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "First name cannot be blank")
    private String lastName;
}
