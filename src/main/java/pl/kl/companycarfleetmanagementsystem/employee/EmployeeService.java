package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Transactional
    public Employee editEmployee(UpdateEmployeeRequest request) {
        final Employee employee = employeeRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Employee with id: " + request.getId() + " not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());

        return employeeRepository.save(employee);
    }
}
