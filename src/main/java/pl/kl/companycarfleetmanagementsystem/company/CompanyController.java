package pl.kl.companycarfleetmanagementsystem.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/company")
public class CompanyController {

    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody @Valid CreateCompanyRequest request) {
        final Company company = companyService.createCompany(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyMapper.mapCompanyToCompanyManager(company));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        final List<Company> companies = companyService.fetchAllCompanies();

        if (companies.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<>());
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(companies.stream()
                            .map(companyMapper::mapCompanyToCompanyManager)
                            .collect(Collectors.toList()));
        }
    }
}
