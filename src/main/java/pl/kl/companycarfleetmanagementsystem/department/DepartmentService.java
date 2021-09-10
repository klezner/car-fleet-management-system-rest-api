package pl.kl.companycarfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(CreateDepartmentRequest request) {
        final Department department = Department.builder()
                .name(request.getName())
                .abbreviation(request.getAbbreviation())
                .comment(request.getComment())
                .build();

        return departmentRepository.save(department);
    }
}
