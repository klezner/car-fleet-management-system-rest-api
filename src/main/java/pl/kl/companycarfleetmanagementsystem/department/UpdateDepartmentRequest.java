package pl.kl.companycarfleetmanagementsystem.department;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UpdateDepartmentRequest {

    @NotNull(message = "Department id is mandatory")
    private Long id;
    @NotBlank(message = "Department name cannot be blank")
    private String name;
    @NotBlank(message = "Department abbreviation cannot be blank")
    private String abbreviation;
    private String comment;
    @NotNull
    private Long companyId;
}
