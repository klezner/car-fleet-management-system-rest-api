package pl.kl.companycarfleetmanagementsystem.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/trip")
public class TripController {

    private final TripMapper tripMapper;
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponse> addTrip(@RequestBody @Valid CreateTripRequest request) {

        final Trip trip = tripService.createTrip(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripMapper.mapTripToTripResponse(trip));
    }

    @PutMapping
    public ResponseEntity<TripResponse> updateTrip(@RequestBody @Valid UpdateTripRequest request) {
        final Trip trip = tripService.editTrip(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripMapper.mapTripToTripResponse(trip));
    }
}
