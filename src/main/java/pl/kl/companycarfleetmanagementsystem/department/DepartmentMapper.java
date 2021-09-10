package pl.kl.companycarfleetmanagementsystem.department;

import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {

        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .abbreviation(department.getAbbreviation())
                .comment(department.getComment())
                .build();
    }
}
