package pl.kl.companycarfleetmanagementsystem.company;

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
}
