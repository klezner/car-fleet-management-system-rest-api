package pl.kl.companycarfleetmanagementsystem.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Car brand cannot be blank")
    private String brand;
    @NotBlank(message = "Car model cannot be blank")
    private String model;
    @NotBlank(message = "Car registration number cannot be blank")
    @Length(min = 3, max = 7, message = "Incorrect car registration number length")
    private String registrationNumber;
    @NotBlank(message = "Car vin number cannot be blank")
    @Length(min = 17, max = 17, message = "Incorrect car vin number length")
    private String vinNumber;
    @NotNull(message = "Car production year cannot be blank")
    @Min(2000)
    @Max(2050)
    private Integer productionYear;
}
