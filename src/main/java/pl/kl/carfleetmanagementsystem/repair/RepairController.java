package pl.kl.carfleetmanagementsystem.repair;

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
@RequestMapping(path = "repair")
@Api(value = "Repair Controller")
public class RepairController {

    private final RepairMapper repairMapper;
    private final RepairService repairService;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new repair", notes = "Allows you to add a new repair in the form of a json request. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<RepairResponse> addRepair(@RequestBody @Valid CreateRepairRequest request) {

        final Repair repair = repairService.createRepair(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repairMapper.mapRepairToRepairResponse(repair));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all repairs", notes = "Allows you to get a list of all repairs. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
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

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update repair", notes = "Allows you to update a repair in the form of a json request. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<RepairResponse> updateRepair(@RequestBody @Valid UpdateRepairRequest request) {
        final Repair repair = repairService.editRepair(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repairMapper.mapRepairToRepairResponse(repair));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get repair by id", notes = "Allows you to get repair by id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<RepairResponse> getRepairById(@PathVariable Long id) {
        final Repair repair = repairService.fetchRepairById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repairMapper.mapRepairToRepairResponse(repair));
    }

    @GetMapping(path = "/carworkshop/{id}", produces = "application/json")
    @ApiOperation(value = "Get repairs by car workshop id", notes = "Allows you to get repairs by car workshop id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<RepairResponse>> getRepairsByCarWorkshopId(@PathVariable Long id) {
        final List<Repair> repairs = repairService.fetchRepairsByCarWorkshopId(id);

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

    @GetMapping(path = "/trip/{id}", produces = "application/json")
    @ApiOperation(value = "Get repairs by trip id", notes = "Allows you to get repairs by trip id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<RepairResponse>> getRepairsByTripId(@PathVariable Long id) {
        final List<Repair> repairs = repairService.fetchRepairsByTripId(id);

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
