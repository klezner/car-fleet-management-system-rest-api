package pl.kl.companycarfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(CreateCompanyRequest request) {
        final Company company = Company.builder()
                .name(request.getName())
                .build();

        return companyRepository.save(company);
    }
}
