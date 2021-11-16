package pl.kl.carfleetmanagementsystem.company;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Company Controller")
public class CompanyController {

    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add new company", notes = "Allows you to add a new company in the form of a json request")
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody @Valid CreateCompanyRequest request) {
        final Company company = companyService.createCompany(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyMapper.mapCompanyToCompanyManager(company));
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all companies", notes = "Allows you to get a list of all companies")
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

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update company", notes = "Allows you to update a company in the form of a json request")
    public ResponseEntity<CompanyResponse> updateCompany(@RequestBody @Valid UpdateCompanyRequest request) {
        final Company company = companyService.editCompany(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyMapper.mapCompanyToCompanyManager(company));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiOperation(value = "Get company by id", notes = "Allows you to get a company by id")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        final Company company = companyService.fetchCompanyById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyMapper.mapCompanyToCompanyManager(company));
    }
}
