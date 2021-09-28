package pl.kl.companycarfleetmanagementsystem.carworkshop;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarWorkshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
