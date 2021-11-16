package pl.kl.carfleetmanagementsystem.refueling;

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
@RequestMapping(path = "/refueling")
@Api(value = "Refueling Controller")
public class RefuelingController {

    private final RefuelingMapper refuelingMapper;
    private final RefuelingService refuelingService;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new refueling", notes = "Allows you to add a new refueling in the form of a json request")
    public ResponseEntity<RefuelingResponse> addRefueling(@RequestBody @Valid CreateRefuelingRequest request) {

        final Refueling refueling = refuelingService.createRefueling(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(refuelingMapper.mapRefuelingToRefuelingResponse(refueling));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all refuelings", notes = "Allows you to get a list of all refuelings")
    public ResponseEntity<List<RefuelingResponse>> getAllRefuelings() {
        final List<Refueling> refuelings = refuelingService.fetchAllRefuelings();

        if (refuelings.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(refuelings.stream()
                            .map(refuelingMapper::mapRefuelingToRefuelingResponse)
                            .collect(Collectors.toList()));
        }
    }

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update refueling", notes = "Allows you to update a refueling in the form of a json request")
    public ResponseEntity<RefuelingResponse> updateRefueling(@RequestBody @Valid UpdateRefuelingRequest request) {
        final Refueling refueling = refuelingService.editRefueling(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(refuelingMapper.mapRefuelingToRefuelingResponse(refueling));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get refueling by id", notes = "Allows you to get a refueling by id")
    public ResponseEntity<RefuelingResponse> getRefuelingById(@PathVariable Long id) {
        final Refueling refueling = refuelingService.fetchRefuelingById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(refuelingMapper.mapRefuelingToRefuelingResponse(refueling));
    }

    @GetMapping(path = "/trip/{id}", produces = "application/json")
    @ApiOperation(value = "Get refuelings by trip id", notes = "Allows you to get refuelings by trip id")
    public ResponseEntity<List<RefuelingResponse>> getRefuelingsByTripId(@PathVariable Long id) {
        final List<Refueling> refuelings = refuelingService.fetchRefuelingsByTripId(id);

        if (refuelings.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(refuelings.stream()
                            .map(refuelingMapper::mapRefuelingToRefuelingResponse)
                            .collect(Collectors.toList()));
        }
    }
}
