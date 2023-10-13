package preonboarding.wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.company.Company;
import preonboarding.wanted.backend.data.company.CompanyInfoDto;
import preonboarding.wanted.backend.data.company.CompanyUpdateDto;
import preonboarding.wanted.backend.data.company.CompanyRequestDto;
import preonboarding.wanted.backend.exception.CompanyNotFoundException;
import preonboarding.wanted.backend.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public Long save(CompanyRequestDto requestDto) {
        return companyRepository.save(Company.of(requestDto)).getId();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Transactional
    public Long update(Long id, CompanyUpdateDto companyUpdateDto) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        company.update(companyUpdateDto);
        return company.getId();
    }

    public List<CompanyInfoDto> findAll() {
        return companyRepository.findAll().stream().map(Company::toInfoDto).toList();
    }

    @Transactional
    public void delete(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        companyRepository.delete(company);
    }
}
