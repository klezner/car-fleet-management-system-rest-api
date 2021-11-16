package pl.kl.carfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.carfleetmanagementsystem.company.CompanyMapper;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    private final CompanyMapper companyMapper;

    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {

        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .abbreviation(department.getAbbreviation())
                .comment(department.getComment())
                .company(companyMapper.mapCompanyToCompanyManager(department.getCompany()))
                .build();
    }
}
