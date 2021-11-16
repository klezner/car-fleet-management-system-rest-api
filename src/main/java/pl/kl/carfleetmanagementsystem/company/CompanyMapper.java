package pl.kl.carfleetmanagementsystem.company;

import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse mapCompanyToCompanyManager(Company company) {

        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
