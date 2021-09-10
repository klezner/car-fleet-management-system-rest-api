package pl.kl.companycarfleetmanagementsystem.company;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateCompanyRequest {

    @NotNull(message = "Company id is mandatory")
    private Long id;
    @NotBlank(message = "Company name cannot be blank")
    private String name;
}
