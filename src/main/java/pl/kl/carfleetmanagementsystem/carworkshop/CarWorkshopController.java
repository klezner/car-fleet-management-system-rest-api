package pl.kl.carfleetmanagementsystem.carworkshop;

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
@RequestMapping(path = "carworkshop")
@Api(value = "Car Workshop Controller")
public class CarWorkshopController {

    private final CarWorkshopMapper carWorkshopMapper;
    private final CarWorkshopService carWorkshopService;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new car workshop", notes = "Allows you to add a new car workshop in the form of a json request. Access with ADMIN role.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarWorkshopResponse> addCarWorkshop(@RequestBody @Valid CreateCarWorkshopRequest request) {

        final CarWorkshop carWorkshop = carWorkshopService.createCarWorkshop(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(carWorkshop));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all car workshops", notes = "Allows you to get a list of all car workshops. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<CarWorkshopResponse>> getAllCarWorkshops() {
        final List<CarWorkshop> carWorkshops = carWorkshopService.fetchAllCarWorkshops();

        if (carWorkshops.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(carWorkshops.stream()
                            .map(carWorkshopMapper::mapCarWorkshopToCarWorkshopResponse)
                            .collect(Collectors.toList()));
        }
    }

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update car workshop", notes = "Allows you to update a car in the form of a json request. Access with ADMIN role.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CarWorkshopResponse> updateCarWorkshops(@RequestBody @Valid UpdateCarWorkshopRequest request) {
        final CarWorkshop carWorkshop = carWorkshopService.editCarWorkshop(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(carWorkshop));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get car workshop by id", notes = "Allows you to get a car workshop by id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<CarWorkshopResponse> getCarWorkshopById(@PathVariable Long id) {
        final CarWorkshop carWorkshop = carWorkshopService.fetchCarWorkshopById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(carWorkshop));
    }
}
