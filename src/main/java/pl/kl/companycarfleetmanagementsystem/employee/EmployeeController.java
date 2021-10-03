package pl.kl.companycarfleetmanagementsystem.employee;

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
@RequestMapping(path = "/employee")
@Api(value = "Employee Controller")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    @PostMapping
    @ApiOperation(value = "Add new employee", notes = "Allows you to add a new employee in the form of a json request")
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        final Employee employee = employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeMapper.mapEmployeeToEmployeeResponse(employee));
    }

    @GetMapping
    @ApiOperation(value = "Get all employees", notes = "Allows you to get a list of all employees")
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

    @PutMapping
    @ApiOperation(value = "Update employee", notes = "Allows you to update an employee in the form of a json request")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest request) {
        final Employee employee = employeeService.editEmployee(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeMapper.mapEmployeeToEmployeeResponse(employee));
    }
}
