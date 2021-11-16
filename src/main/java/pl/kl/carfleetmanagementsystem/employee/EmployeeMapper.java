package pl.kl.carfleetmanagementsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.department.DepartmentMapper;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final DepartmentMapper departmentMapper;

    public EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {

        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .department(departmentMapper.mapDepartmentToDepartmentResponse(employee.getDepartment()))
                .build();
    }
}
