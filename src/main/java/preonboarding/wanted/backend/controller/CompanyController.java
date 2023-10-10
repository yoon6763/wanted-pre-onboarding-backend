package preonboarding.wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.data.company.CompanyInfoDto;
import preonboarding.wanted.backend.data.company.CompanyRequestDto;
import preonboarding.wanted.backend.data.company.CompanyUpdateDto;
import preonboarding.wanted.backend.service.CompanyService;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/api/company/{id}")
    public CompanyInfoDto getCompanyInfo(@PathVariable Long id) {
        return companyService.findById(id).toInfoDto();
    }

    @PostMapping("/api/company")
    public Long saveCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        log.info("companyRequestDto: {}", companyRequestDto);
        return companyService.save(companyRequestDto);
    }

    @PutMapping("/api/company/{id}")
    public CompanyInfoDto updateCompanyInfo(@PathVariable Long id, @RequestBody CompanyUpdateDto companyUpdateDto) {
        companyService.update(id, companyUpdateDto);
        return companyService.findById(id).toInfoDto();
    }

    @DeleteMapping("/api/company/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
    }

    @GetMapping("/api/company")
    public List<CompanyInfoDto> getCompanyList() {
        return companyService.findAll();
    }
}
