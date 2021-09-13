package pl.kl.companycarfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.company.Company;
import pl.kl.companycarfleetmanagementsystem.company.CompanyService;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final CompanyService companyService;
    private final DepartmentRepository departmentRepository;

    public Department createDepartment(CreateDepartmentRequest request) {
        final Company company = companyService.fetchCompanyById(request.getCompanyId());

        final Department department = Department.builder()
                .name(request.getName())
                .abbreviation(request.getAbbreviation())
                .comment(request.getComment())
                .company(company)
                .build();

        return departmentRepository.save(department);
    }
}
