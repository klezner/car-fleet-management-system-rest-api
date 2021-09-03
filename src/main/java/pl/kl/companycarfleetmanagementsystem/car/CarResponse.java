package pl.kl.companycarfleetmanagementsystem.car;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarResponse {

    private Long id;
    private String brand;
    private String model;
    private String registrationNumber;
    private String vinNumber;
    private Integer productionYear;
}
