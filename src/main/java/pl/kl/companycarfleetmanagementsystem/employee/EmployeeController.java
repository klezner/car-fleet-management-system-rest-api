package pl.kl.companycarfleetmanagementsystem.employee;

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
public class EmployeeController {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        final Employee employee = employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeMapper.mapEmployeeToEmployeeResponse(employee));
    }

    @GetMapping
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
}
