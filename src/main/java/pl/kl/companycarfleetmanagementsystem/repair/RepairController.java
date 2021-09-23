package pl.kl.companycarfleetmanagementsystem.repair;

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
@RequestMapping(path = "repair")
public class RepairController {

    private final RepairMapper repairMapper;
    private final RepairService repairService;

    @PostMapping
    public ResponseEntity<RepairResponse> addRepair(@RequestBody @Valid CreateRepairRequest request) {

        final Repair repair = repairService.createRepair(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repairMapper.mapRepairToRepairResponse(repair));
    }
}
