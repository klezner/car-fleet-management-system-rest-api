package pl.kl.companycarfleetmanagementsystem.repair;

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

    @GetMapping
    public ResponseEntity<List<RepairResponse>> getAllRepairs() {
        final List<Repair> repairs = repairService.fetchAllRepairs();

        if (repairs.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(repairs.stream()
                            .map(repairMapper::mapRepairToRepairResponse)
                            .collect(Collectors.toList()));
        }
    }
}
