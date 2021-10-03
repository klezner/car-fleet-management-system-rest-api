package pl.kl.companycarfleetmanagementsystem.department;

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
@RequestMapping(path = "/department")
@Api(value = "Department Controller")
public class DepartmentController {

    private final DepartmentMapper departmentMapper;
    private final DepartmentService departmentService;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new department", notes = "Allows you to add a new department in the form of a json request")
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody @Valid CreateDepartmentRequest request) {
        final Department department = departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentMapper.mapDepartmentToDepartmentResponse(department));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all departments", notes = "Allows you to get a list of all departments")
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

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update department", notes = "Allows you to update a department in the form of a json request")
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody @Valid UpdateDepartmentRequest request) {
        final Department department = departmentService.editDepartment(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentMapper.mapDepartmentToDepartmentResponse(department));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get departments by id", notes = "Allows you to get a department by id")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        final Department department = departmentService.fetchDepartmentById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentMapper.mapDepartmentToDepartmentResponse(department));
    }
}
