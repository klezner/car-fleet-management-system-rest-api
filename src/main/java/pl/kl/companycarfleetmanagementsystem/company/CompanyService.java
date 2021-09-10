package pl.kl.companycarfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    public List<Company> fetchAllCompanies() {

        return companyRepository.findAll();
    }

    @Transactional
    public Company editCompany(UpdateCompanyRequest request) {
        final Company company = companyRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Company with id: " + request.getId() + " not found"));

        company.setName(request.getName());

        return companyRepository.save(company);
    }
}
