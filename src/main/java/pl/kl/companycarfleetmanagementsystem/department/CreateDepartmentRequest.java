package pl.kl.companycarfleetmanagementsystem.department;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateDepartmentRequest {

    @NotBlank(message = "Department name cannot be blank")
    private String name;
    @NotBlank(message = "Department abbreviation cannot be blank")
    private String abbreviation;
    private String comment;
}
