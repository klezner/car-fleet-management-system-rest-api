package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(CreateEmployeeRequest request) {
        final Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        return employeeRepository.save(employee);
    }

    public List<Employee> fetchAllEmployees() {

        return employeeRepository.findAll();
    }
}
