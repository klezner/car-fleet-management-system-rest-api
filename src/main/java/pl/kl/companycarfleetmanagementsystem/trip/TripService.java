package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.companycarfleetmanagementsystem.car.Car;
import pl.kl.companycarfleetmanagementsystem.car.CarService;
import pl.kl.companycarfleetmanagementsystem.employee.Employee;
import pl.kl.companycarfleetmanagementsystem.employee.EmployeeService;
import pl.kl.companycarfleetmanagementsystem.validator.DateValidator;
import pl.kl.companycarfleetmanagementsystem.validator.MeterStatusValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TripService {

    private final EmployeeService employeeService;
    private final CarService carService;
    private final TripRepository tripRepository;

    private final LocalDate SYSTEM_START_DATE = LocalDate.of(2000, 1, 1);
    private final Integer SYSTEM_START_METER_STATUS = 0;

    public Trip createTrip(CreateTripRequest request) {

        final Car car = carService.fetchCarById(request.getCarId());
        final Employee employee = employeeService.fetchEmployeeById(request.getEmployeeId());

        final LocalDate lastReturnDate = car.getTrips().stream()
                .map(Trip::getReturnDate)
                .max(LocalDate::compareTo)
                .orElse(SYSTEM_START_DATE);

        final Integer lastReturnMeterStatus = car.getTrips().stream()
                .map(Trip::getReturnMeterStatus)
                .max(Integer::compareTo)
                .orElse(SYSTEM_START_METER_STATUS);

        DateValidator.validateTripDateOnTripCreate(request.getDepartureDate(), request.getReturnDate(), lastReturnDate);
        MeterStatusValidator.validateMeterStatusOnTripCreate(request.getDepartureMeterStatus(), request.getReturnMeterStatus(), lastReturnMeterStatus);

        final Trip trip = Trip.builder()
                .departureDate(request.getDepartureDate())
                .returnDate(request.getReturnDate())
                .departureMeterStatus(request.getDepartureMeterStatus())
                .returnMeterStatus(request.getReturnMeterStatus())
                .comments(request.getComments())
                .car(car)
                .employee(employee)
                .build();

        return tripRepository.save(trip);
    }

    public List<Trip> fetchAllTrips() {

        return tripRepository.findAll();
    }

    @Transactional
    public Trip editTrip(UpdateTripRequest request) {
        final Trip trip = tripRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Trip with id: " + request.getId() + "not found"));

        final Car car = carService.fetchCarById(request.getCarId());

        DateValidator.validateTripDateOnTripEdit(request.getDepartureDate(), request.getReturnDate());
        MeterStatusValidator.validateMeterStatusOnTripEdit(request.getDepartureMeterStatus(), request.getReturnMeterStatus());

        trip.setDepartureDate(request.getDepartureDate());
        trip.setReturnDate(request.getReturnDate());
        trip.setDepartureMeterStatus(request.getDepartureMeterStatus());
        trip.setReturnMeterStatus(request.getReturnMeterStatus());
        trip.setComments(request.getComments());
        trip.setCar(car);

        return tripRepository.save(trip);
    }

    public Trip fetchTripById(Long id) {

        return tripRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Trip with id: " + id + " not found"));
    }

    public List<Trip> fetchTripsByCarId(Long id) {

        return tripRepository.getAllByCarId(id);
    }
}
