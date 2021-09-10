package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.Builder;
import lombok.Getter;
import pl.kl.companycarfleetmanagementsystem.car.CarResponse;
import pl.kl.companycarfleetmanagementsystem.employee.EmployeeResponse;

import java.time.LocalDate;

@Getter
@Builder
public class TripResponse {

    private Long id;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Integer departureMeterStatus;
    private Integer returnMeterStatus;
    private String comments;
    private CarResponse car;
    private EmployeeResponse employee;
}
