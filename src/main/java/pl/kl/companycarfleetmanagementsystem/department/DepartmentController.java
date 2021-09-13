package pl.kl.companycarfleetmanagementsystem.department;

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
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentMapper departmentMapper;
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody @Valid CreateDepartmentRequest request) {
        final Department department = departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentMapper.mapDepartmentToDepartmentResponse(department));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        final List<Department> departments = departmentService.fetchAllDepartments();

        if (departments.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(departments.stream()
                            .map(departmentMapper::mapDepartmentToDepartmentResponse)
                            .collect(Collectors.toList()));
        }
    }

    @PutMapping
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody @Valid UpdateDepartmentRequest request) {
        final Department department = departmentService.editDepartment(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentMapper.mapDepartmentToDepartmentResponse(department));
    }
}
