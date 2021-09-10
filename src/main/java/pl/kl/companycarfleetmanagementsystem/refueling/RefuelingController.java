package pl.kl.companycarfleetmanagementsystem.refueling;

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
@RequestMapping(path = "/refueling")
public class RefuelingController {

    private final RefuelingMapper refuelingMapper;
    private final RefuelingService refuelingService;

    @PostMapping
    public ResponseEntity<RefuelingResponse> addRefueling(@RequestBody @Valid CreateRefuelingRequest request) {

        final Refueling refueling = refuelingService.createRefueling(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(refuelingMapper.mapRefuelingToRefuelingResponse(refueling));
    }
}
