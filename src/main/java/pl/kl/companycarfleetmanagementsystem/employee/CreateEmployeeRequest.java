package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class CreateEmployeeRequest {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "First name cannot be blank")
    private String lastName;
}
