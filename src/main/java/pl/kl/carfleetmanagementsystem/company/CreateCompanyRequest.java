package pl.kl.carfleetmanagementsystem.company;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateCompanyRequest {

    @NotBlank(message = "Company name cannot be blank")
    private String name;
}
