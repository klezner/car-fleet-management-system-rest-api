package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.car.Car;
import pl.kl.companycarfleetmanagementsystem.car.CarService;
import pl.kl.companycarfleetmanagementsystem.exceptions.CarWithAssignedFleetCardException;
import pl.kl.companycarfleetmanagementsystem.validator.DateValidator;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FleetCardService {

    private final CarService carService;
    private final FleetCardRepository fleetCardRepository;

    public FleetCard createFleetCard(CreateFleetCardRequest request) {

        final LocalDate systemStartDate = LocalDate.of(2000, 1, 1);

        final Car car = carService.fetchCarById(request.getCarId());

        if (car.getFleetCard() != null) {
            throw new CarWithAssignedFleetCardException("Car with id: " + request.getCarId() + " already has assigned fleet card");
        }

        DateValidator.validateFleetCardExpirationDate(request.getExpirationDate(), systemStartDate);

        final FleetCard fleetCard = FleetCard.builder()
                .number(request.getNumber())
                .expirationDate(request.getExpirationDate())
                .type(request.getType())
                .car(car)
                .build();

        return fleetCardRepository.save(fleetCard);
    }

    public List<FleetCard> fetchAllFleetCards() {

        return fleetCardRepository.findAll();
    }
}
