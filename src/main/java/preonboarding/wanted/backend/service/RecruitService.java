package preonboarding.wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.company.Company;
import preonboarding.wanted.backend.data.recruit.Recruit;
import preonboarding.wanted.backend.data.recruit.RecruitDetailDto;
import preonboarding.wanted.backend.data.recruit.RecruitRequestDto;
import preonboarding.wanted.backend.data.recruit.RecruitInfoDto;
import preonboarding.wanted.backend.repository.CompanyRepository;
import preonboarding.wanted.backend.repository.RecruitRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Long registerRecruit(RecruitRequestDto requestDto) {
        Company company = companyRepository.findById(requestDto.getCompanyId()).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        Recruit recruit = Recruit.of(company, requestDto);
        return recruitRepository.save(recruit).getId();
    }

    public RecruitDetailDto findById(Long id) {
        Recruit recruit = recruitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        List<Long> otherRecruitIds = recruitRepository.findAllByCompanyId(recruit.getCompany().getId()).stream().map(Recruit::getId).toList();
        return recruit.toDetailDto(otherRecruitIds);
    }

    public List<RecruitInfoDto> findAll() {
        return recruitRepository.findAll().stream().map(Recruit::toInfoDto).toList();
    }

    public void deleteRecruit(Long id) {
        recruitRepository.deleteById(id);
    }
}
