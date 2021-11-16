package pl.kl.carfleetmanagementsystem.employee;

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
@RequestMapping(path = "/employee")
@Api(value = "Employee Controller")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new employee", notes = "Allows you to add a new employee in the form of a json request. Access with ADMIN role.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        final Employee employee = employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeMapper.mapEmployeeToEmployeeResponse(employee));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all employees", notes = "Allows you to get a list of all employees. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        final List<Employee> employees = employeeService.fetchAllEmployees();

        if (employees.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(employees.stream()
                            .map(employeeMapper::mapEmployeeToEmployeeResponse)
                            .collect(Collectors.toList()));
        }
    }

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update employee", notes = "Allows you to update an employee in the form of a json request. Access with ADMIN role.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest request) {
        final Employee employee = employeeService.editEmployee(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeMapper.mapEmployeeToEmployeeResponse(employee));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get employee by id", notes = "Allows you to get an employee by id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        final Employee employee = employeeService.fetchEmployeeById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeMapper.mapEmployeeToEmployeeResponse(employee));
    }

    @GetMapping(path = "/department/{id}", produces = "application/json")
    @ApiOperation(value = "Get employees by department id", notes = "Allows you to get employees by department id. Access with ADMIN and USER roles.")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartmentId(@PathVariable Long id) {
        final List<Employee> employees = employeeService.fetchEmployeesByDepartmentId(id);

        if (employees.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(employees.stream()
                            .map(employeeMapper::mapEmployeeToEmployeeResponse)
                            .collect(Collectors.toList()));
        }
    }
}
