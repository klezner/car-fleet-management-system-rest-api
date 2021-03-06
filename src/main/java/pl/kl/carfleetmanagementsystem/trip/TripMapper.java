package pl.kl.carfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.car.CarMapper;
import pl.kl.carfleetmanagementsystem.employee.EmployeeMapper;

@Component
@RequiredArgsConstructor
public class TripMapper {

    private final EmployeeMapper employeeMapper;
    private final CarMapper carMapper;

    public TripResponse mapTripToTripResponse(Trip trip) {

        return TripResponse.builder()
                .id(trip.getId())
                .departureDate(trip.getDepartureDate())
                .returnDate(trip.getReturnDate())
                .departureMeterStatus(trip.getDepartureMeterStatus())
                .returnMeterStatus(trip.getReturnMeterStatus())
                .comments(trip.getComments())
                .car(carMapper.mapCarToCarResponse(trip.getCar()))
                .employee(employeeMapper.mapEmployeeToEmployeeResponse(trip.getEmployee()))
                .build();
    }
}
