package pl.kl.companycarfleetmanagementsystem.trip;

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
@RequestMapping(path = "/trip")
public class TripController {

    private final TripMapper tripMapper;
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponse> addTrip(@RequestBody @Valid CreateTripRequest request) {
        final Trip trip = tripService.createTrip(
                request.getDepartureDate(),
                request.getReturnDate(),
                request.getDepartureMeterStatus(),
                request.getReturnMeterStatus(),
                request.getComments()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripMapper.mapTripToTripResponse(trip));
    }
}
