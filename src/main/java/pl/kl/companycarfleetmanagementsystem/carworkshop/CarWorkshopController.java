package pl.kl.companycarfleetmanagementsystem.carworkshop;

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
public class CarWorkshopController {

    private final CarWorkshopMapper carWorkshopMapper;
    private final CarWorkshopService carWorkshopService;

    @PostMapping
    public ResponseEntity<CarWorkshopResponse> addCarWorkshop(@RequestBody @Valid CreateCarWorkshopRequest request) {

        final CarWorkshop carWorkshop = carWorkshopService.createCarWorkshop(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(carWorkshop));
    }

    @GetMapping
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
}
