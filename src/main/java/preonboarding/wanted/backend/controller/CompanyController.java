package preonboarding.wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.data.common.RegisterResponse;
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
    public ResponseEntity<CompanyInfoDto> getCompanyInfo(@PathVariable Long id) {
        return ResponseEntity.ok().body(companyService.findById(id).toInfoDto());
    }

    @PostMapping("/api/company")
    public ResponseEntity<RegisterResponse> saveCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        return ResponseEntity.ok().body(new RegisterResponse(companyService.save(companyRequestDto)));
    }

    @PutMapping("/api/company/{id}")
    public ResponseEntity<CompanyInfoDto> updateCompanyInfo(@PathVariable Long id, @RequestBody CompanyUpdateDto companyUpdateDto) {
        companyService.update(id, companyUpdateDto);
        return ResponseEntity.ok().body(companyService.findById(id).toInfoDto());
    }

    @DeleteMapping("/api/company/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
    }

    @GetMapping("/api/company")
    public ResponseEntity<List<CompanyInfoDto>> getCompanyList() {
        return ResponseEntity.ok().body(companyService.findAll());
    }
}
