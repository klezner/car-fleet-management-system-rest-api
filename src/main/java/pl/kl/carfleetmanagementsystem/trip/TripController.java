package pl.kl.carfleetmanagementsystem.trip;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new trip", notes = "Allows you to add a new trip in the form of a json request. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TripResponse> addTrip(@RequestBody @Valid CreateTripRequest request) {
        final Trip trip = tripService.createTrip(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripMapper.mapTripToTripResponse(trip));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all trips", notes = "Allows you to get a list of all trips. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
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

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update trip", notes = "Allows you to update a trip in the form of a json request. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TripResponse> updateTrip(@RequestBody @Valid UpdateTripRequest request) {
        final Trip trip = tripService.editTrip(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripMapper.mapTripToTripResponse(trip));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get trip by id", notes = "Allows you to get a trip by id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TripResponse> getTriById(@PathVariable Long id) {
        final Trip trip = tripService.fetchTripById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripMapper.mapTripToTripResponse(trip));
    }

    @GetMapping(path = "/car/{id}", produces = "application/json")
    @ApiOperation(value = "Get trips by car id", notes = "Allows you to get trips by car id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<TripResponse>> getTriBCarId(@PathVariable Long id) {
        final List<Trip> trips = tripService.fetchTripsByCarId(id);

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
}
