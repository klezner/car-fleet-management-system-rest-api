package pl.kl.companycarfleetmanagementsystem.carworkshop;

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
@RequestMapping(path = "carworkshop")
@Api(value = "Car Workshop Controller")
public class CarWorkshopController {

    private final CarWorkshopMapper carWorkshopMapper;
    private final CarWorkshopService carWorkshopService;

    @PostMapping
    @ApiOperation(value = "Add new car workshop", notes = "Allows you to add a new car workshop in the form of a json request")
    public ResponseEntity<CarWorkshopResponse> addCarWorkshop(@RequestBody @Valid CreateCarWorkshopRequest request) {

        final CarWorkshop carWorkshop = carWorkshopService.createCarWorkshop(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(carWorkshop));
    }

    @GetMapping
    @ApiOperation(value = "Get all car workshops", notes = "Allows you to get a list of all car workshops")
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

    @PutMapping
    @ApiOperation(value = "Update car workshop", notes = "Allows you to update a car in the form of a json request")
    public ResponseEntity<CarWorkshopResponse> updateCarWorkshops(@RequestBody @Valid UpdateCarWorkshopRequest request) {
        final CarWorkshop carWorkshop = carWorkshopService.editCarWorkshop(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(carWorkshop));
    }
}
