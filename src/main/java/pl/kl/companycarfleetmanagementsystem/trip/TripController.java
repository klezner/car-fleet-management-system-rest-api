package pl.kl.companycarfleetmanagementsystem.trip;

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
@RequestMapping(path = "/trip")
@Api(value = "Trip Controller")
public class TripController {

    private final TripMapper tripMapper;
    private final TripService tripService;

    @PostMapping
    @ApiOperation(value = "Add new trip", notes = "Allows you to add a new trip in the form of a json request")
    public ResponseEntity<TripResponse> addTrip(@RequestBody @Valid CreateTripRequest request) {
        final Trip trip = tripService.createTrip(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripMapper.mapTripToTripResponse(trip));
    }

    @GetMapping
    @ApiOperation(value = "Get all trips", notes = "Allows you to get a list of all trips")
    public ResponseEntity<List<TripResponse>> getAllTrips() {
        final List<Trip> trips = tripService.fetchAllTrips();

        if (trips.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(trips.stream()
                            .map(tripMapper::mapTripToTripResponse)
                            .collect(Collectors.toList()));
        }
    }

    @PutMapping
    @ApiOperation(value = "Update trip", notes = "Allows you to update a trip in the form of a json request")
    public ResponseEntity<TripResponse> updateTrip(@RequestBody @Valid UpdateTripRequest request) {
        final Trip trip = tripService.editTrip(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripMapper.mapTripToTripResponse(trip));
    }
}
