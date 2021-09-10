package pl.kl.companycarfleetmanagementsystem.department;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentResponse {

    private Long id;
    private String name;
    private String abbreviation;
    private String comment;
}
