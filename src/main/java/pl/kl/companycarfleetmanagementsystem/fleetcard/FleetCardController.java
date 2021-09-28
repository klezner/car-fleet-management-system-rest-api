package pl.kl.companycarfleetmanagementsystem.fleetcard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fleetcard")
public class FleetCardController {

    private final FleetCardMapper fleetCardMapper;
    private final FleetCardService fleetCardService;

    @PostMapping
    public ResponseEntity<FleetCardResponse> addFleetCard(@RequestBody @Valid CreateFleetCardRequest request) {

        final FleetCard fleetCard = fleetCardService.createFleetCard(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fleetCardMapper.mapFleetCardToFleetCardResponse(fleetCard));
    }
}
