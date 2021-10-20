package pl.kl.companycarfleetmanagementsystem.repair;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class CreateRepairRequest {

    @NotNull(message = "Left date at the workshop cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leftDate;
    @NotNull(message = "Left meter status at workshop cannot be blank")
    private Integer leftMeterStatus;
    @NotBlank(message = "Repair invoice number cannot be blank")
    private String invoiceNumber;
    @NotNull(message = "Repair invoice dater cannot be blank")
    private LocalDate invoiceDate;
    @NotNull(message = "Repair cost cannot be blank")
    @Min(0)
    private Double repairCost;
    @NotNull(message = "Pickup date at the workshop date cannot be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;
    @NotNull(message = "Trip id cannot be blank")
    private Long tripId;
    @NotNull(message = "Car workshop id cannot be blank")
    private Long carWorkshopId;
}
