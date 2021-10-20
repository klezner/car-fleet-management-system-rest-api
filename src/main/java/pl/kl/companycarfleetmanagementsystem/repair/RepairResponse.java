package pl.kl.companycarfleetmanagementsystem.repair;

import lombok.Builder;
import lombok.Getter;
import pl.kl.companycarfleetmanagementsystem.carworkshop.CarWorkshopResponse;
import pl.kl.companycarfleetmanagementsystem.trip.TripResponse;

import java.time.LocalDate;

@Getter
@Builder
public class RepairResponse {

    private Long id;
    private LocalDate leftDate;
    private Integer leftMeterStatus;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private Double repairCost;
    private LocalDate pickupDate;
    private TripResponse trip;
    private CarWorkshopResponse carWorkshop;
}
