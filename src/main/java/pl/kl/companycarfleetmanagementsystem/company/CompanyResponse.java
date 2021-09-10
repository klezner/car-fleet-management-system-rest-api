package pl.kl.companycarfleetmanagementsystem.company;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponse {

    private Long id;
    private String name;
}
