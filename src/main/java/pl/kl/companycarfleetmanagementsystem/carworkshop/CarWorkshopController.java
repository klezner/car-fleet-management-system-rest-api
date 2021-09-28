package pl.kl.companycarfleetmanagementsystem.carworkshop;

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
}
