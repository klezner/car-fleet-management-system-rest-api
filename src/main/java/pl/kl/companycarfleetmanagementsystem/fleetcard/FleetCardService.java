package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.car.Car;
import pl.kl.companycarfleetmanagementsystem.car.CarService;

@Service
@RequiredArgsConstructor
public class FleetCardService {

    private final CarService carService;
    private final FleetCardRepository fleetCardRepository;

    public FleetCard createFleetCard(CreateFleetCardRequest request) {

        final Car car = carService.fetchCarById(request.getCarId());

        final FleetCard fleetCard = FleetCard.builder()
                .number(request.getNumber())
                .expirationDate(request.getExpirationDate())
                .type(request.getType())
                .car(car)
                .build();

        return fleetCardRepository.save(fleetCard);
    }
}
