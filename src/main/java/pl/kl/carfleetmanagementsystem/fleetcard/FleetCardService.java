package pl.kl.carfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.car.Car;
import pl.kl.carfleetmanagementsystem.car.CarService;
import pl.kl.carfleetmanagementsystem.exceptions.CarWithAssignedFleetCardException;
import pl.kl.carfleetmanagementsystem.validator.DateValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FleetCardService {

    private final CarService carService;
    private final FleetCardRepository fleetCardRepository;

    private final LocalDate SYSTEM_START_DATE = LocalDate.of(2000, 1, 1);

    public FleetCard createFleetCard(CreateFleetCardRequest request) {

        final Car car = carService.fetchCarById(request.getCarId());

        if (car.getFleetCard() != null) {
            throw new CarWithAssignedFleetCardException("Car with id: " + request.getCarId() + " already has assigned fleet card");
        }

        DateValidator.validateFleetCardExpirationDate(request.getExpirationDate(), SYSTEM_START_DATE);

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

    @Transactional
    public FleetCard editFleetCard(UpdateFleetCardRequest request) {

        final Car car = carService.fetchCarById(request.getCarId());

        DateValidator.validateFleetCardExpirationDate(request.getExpirationDate(), SYSTEM_START_DATE);

        final FleetCard fleetCard = fleetCardRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Fleet card with id: " + request.getId() + " not found"));

        fleetCard.setNumber(request.getNumber());
        fleetCard.setExpirationDate(request.getExpirationDate());
        fleetCard.setType(request.getType());
        fleetCard.setCar(car);

        return fleetCardRepository.save(fleetCard);
    }

    public FleetCard fetchFleetCardById(Long id) {

        return fleetCardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Fleet card with id: " + id + " not found"));

    }
}
