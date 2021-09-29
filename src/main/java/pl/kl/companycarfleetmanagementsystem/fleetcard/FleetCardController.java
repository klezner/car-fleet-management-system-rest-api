package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kl.companycarfleetmanagementsystem.car.CarService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fleetcard")
public class FleetCardController {

    private final CarService carService;
    private final FleetCardMapper fleetCardMapper;
    private final FleetCardService fleetCardService;

    @PostMapping
    public ResponseEntity<FleetCardResponse> addFleetCard(@RequestBody @Valid CreateFleetCardRequest request) {

        final FleetCard fleetCard = fleetCardService.createFleetCard(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fleetCardMapper.mapFleetCardToFleetCardResponse(fleetCard));
    }

    @GetMapping
    public ResponseEntity<List<FleetCardResponse>> getAllFleetCards() {
        final List<FleetCard> fleetCards = fleetCardService.fetchAllFleetCards();

        if (fleetCards.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(fleetCards.stream()
                            .map(fleetCardMapper::mapFleetCardToFleetCardResponse)
                            .collect(Collectors.toList()));
        }
    }
}
