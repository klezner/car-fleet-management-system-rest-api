package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
public class UpdateFleetCardRequest {

    @NotNull(message = "Fleet card id is mandatory")
    private Long id;
    @NotBlank(message = "Fleet card number cannot be blank")
    private String number;
    @NotNull(message = "Fleet card expiration date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    @NotBlank(message = "Fleet card type cannot be blank")
    private String type;
    @NotNull(message = "Car id cannot be blank")
    private Long carId;
}
