package pl.kl.companycarfleetmanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentMapper departmentMapper;
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody @Valid CreateDepartmentRequest request) {
        final Department department = departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentMapper.mapDepartmentToDepartmentResponse(department));
    }
}
