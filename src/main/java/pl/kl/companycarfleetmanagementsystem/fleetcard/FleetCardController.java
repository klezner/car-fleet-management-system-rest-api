package pl.kl.companycarfleetmanagementsystem.fleetcard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fleetcard")
@Api(value = "Fleet card Controller")
public class FleetCardController {

    private final FleetCardMapper fleetCardMapper;
    private final FleetCardService fleetCardService;

    @PostMapping
    @ApiOperation(value = "Add new fleet card", notes = "Allows you to add a new fleet card in the form of a json request")
    public ResponseEntity<FleetCardResponse> addFleetCard(@RequestBody @Valid CreateFleetCardRequest request) {

        final FleetCard fleetCard = fleetCardService.createFleetCard(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fleetCardMapper.mapFleetCardToFleetCardResponse(fleetCard));
    }

    @GetMapping
    @ApiOperation(value = "Get all fleet cards", notes = "Allows you to get a list of all fleet cards")
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

    @PutMapping
    @ApiOperation(value = "Update fleet card", notes = "Allows you to update a fleet card in the form of a json request")
    public ResponseEntity<FleetCardResponse> updateFleetCard(@RequestBody @Valid UpdateFleetCardRequest request) {
        final FleetCard fleetCard = fleetCardService.editFleetCard(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fleetCardMapper.mapFleetCardToFleetCardResponse(fleetCard));
    }
}
