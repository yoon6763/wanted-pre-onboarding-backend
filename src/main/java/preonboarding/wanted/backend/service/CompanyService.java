package preonboarding.wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import preonboarding.wanted.backend.data.company.Company;
import preonboarding.wanted.backend.data.company.CompanyInfoDto;
import preonboarding.wanted.backend.data.company.CompanyUpdateDto;
import preonboarding.wanted.backend.data.company.CompanyRequestDto;
import preonboarding.wanted.backend.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Long save(CompanyRequestDto requestDto) {
        return companyRepository.save(Company.of(requestDto)).getId();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
    }

    public Long update(Long id, CompanyUpdateDto editDto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        company.update(editDto);
        return company.getId();
    }

    public List<CompanyInfoDto> findAll() {
        return companyRepository.findAll().stream()
                .map(Company::toInfoDto)
                .toList();
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
