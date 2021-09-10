package pl.kl.companycarfleetmanagementsystem.refueling;

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

    @GetMapping
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

    @PutMapping
    public final ResponseEntity<RefuelingResponse> updateRefueling(@RequestBody @Valid UpdateRefuelingRequest request) {
        final Refueling refueling = refuelingService.editRefueling(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(refuelingMapper.mapRefuelingToRefuelingResponse(refueling));
    }
}
