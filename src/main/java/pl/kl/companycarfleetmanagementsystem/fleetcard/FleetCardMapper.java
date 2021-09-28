package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.companycarfleetmanagementsystem.car.CarMapper;

@Component
@RequiredArgsConstructor
public class FleetCardMapper {

    private final CarMapper carMapper;

    public FleetCardResponse mapFleetCardToFleetCardResponse(FleetCard fleetCard) {

        return FleetCardResponse.builder()
                .id(fleetCard.getId())
                .number(fleetCard.getNumber())
                .expirationDate(fleetCard.getExpirationDate())
                .type(fleetCard.getType())
                .car(carMapper.mapCarToCarResponse(fleetCard.getCar()))
                .build();
    }
}
