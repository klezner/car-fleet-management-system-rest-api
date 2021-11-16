package pl.kl.carfleetmanagementsystem.department;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CreateDepartmentRequest {

    @NotBlank(message = "Department name cannot be blank")
    private String name;
    @NotBlank(message = "Department abbreviation cannot be blank")
    private String abbreviation;
    private String comment;
    @NotNull(message = "Company id cannot be blank")
    private Long companyId;
}
