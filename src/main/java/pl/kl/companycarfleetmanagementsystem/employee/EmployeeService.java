package pl.kl.companycarfleetmanagementsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.companycarfleetmanagementsystem.department.Department;
import pl.kl.companycarfleetmanagementsystem.department.DepartmentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentService departmentService;
    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(CreateEmployeeRequest request) {

        final Department department = departmentService.fetchDepartmentById(request.getDepartmentId());

        final Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .department(department)
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

        final Department department = departmentService.fetchDepartmentById(request.getDepartmentId());

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public Employee fetchEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee with id: " + id + " not found"));
    }

    public List<Employee> fetchEmployeesByDepartmentId(Long id) {

        return employeeRepository.getAllByDepartmentId(id);
    }
}
