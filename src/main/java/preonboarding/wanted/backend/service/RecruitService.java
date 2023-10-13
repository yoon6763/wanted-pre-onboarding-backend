package preonboarding.wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.company.Company;
import preonboarding.wanted.backend.data.recruit.*;
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
        List<Long> otherRecruitIds = getOtherRecruitIds(recruit);
        return recruit.toDetailDto(otherRecruitIds);
    }


    public List<RecruitInfoDto> findAll() {
        return recruitRepository.findAll().stream().map(Recruit::toInfoDto).toList();
    }

    public void deleteRecruit(Long id) {
        recruitRepository.deleteById(id);
    }

    @Transactional
    public void updateRecruit(Long id, RecruitUpdateDto recruitUpdateDto) {
        Recruit recruit = recruitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        recruit.update(recruitUpdateDto);
        recruit.toDetailDto(getOtherRecruitIds(recruit));
    }

    private List<Long> getOtherRecruitIds(Recruit recruit) {
        return recruitRepository.findAllByCompanyId(recruit.getCompany().getId()).stream().map(Recruit::getId).toList();
    }

    public List<RecruitInfoDto> searchRecruit(String keyword) {
        return recruitRepository.searchRecruit(keyword).stream().map(Recruit::toInfoDto).toList();
    }
}
