package pl.kl.companycarfleetmanagementsystem.carworkshop;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class UpdateCarWorkshopRequest {

    @NotNull(message = "Car workshop id is mandatory")
    private Long id;
    @NotBlank(message = "Car workshop name cannot be blank")
    private String name;
    @NotBlank(message = "Zip code cannot be blank")
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Incorrect format of zip code. Correct format is: XX-XXX")
    private String zipCode;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotBlank(message = "Street name cannot be blank")
    private String street;
    @NotBlank(message = "Number of building cannot be blank")
    private String number;
}
