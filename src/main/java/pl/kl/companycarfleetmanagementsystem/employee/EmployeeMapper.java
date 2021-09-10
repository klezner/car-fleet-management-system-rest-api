package pl.kl.companycarfleetmanagementsystem.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {

        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
    }
}
