package pl.kl.carfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.carfleetmanagementsystem.company.Company;
import pl.kl.carfleetmanagementsystem.company.CompanyService;

import java.util.List;
import java.util.NoSuchElementException;

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

    public List<Department> fetchAllDepartments() {

        return departmentRepository.findAll();
    }

    @Transactional
    public Department editDepartment(UpdateDepartmentRequest request) {
        final Department department = departmentRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Department with id: " + request.getId() + "not found"));

        final Company company = companyService.fetchCompanyById(request.getCompanyId());

        department.setName(request.getName());
        department.setAbbreviation(request.getAbbreviation());
        department.setComment(request.getComment());
        department.setCompany(company);

        return departmentRepository.save(department);
    }

    public Department fetchDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Department with id: " + id + " not found"));
    }

    public List<Department> fetchDepartmentsByCompanyId(Long id) {

        return departmentRepository.getAllByCompanyId(id);
    }
}
