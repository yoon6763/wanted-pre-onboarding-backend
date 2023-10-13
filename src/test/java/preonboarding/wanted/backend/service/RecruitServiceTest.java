package preonboarding.wanted.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.company.Company;
import preonboarding.wanted.backend.data.company.CompanyRequestDto;
import preonboarding.wanted.backend.data.recruit.RecruitRequestDto;
import preonboarding.wanted.backend.data.recruit.RecruitUpdateDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class RecruitServiceTest {

    @Autowired
    RecruitService recruitService;

    @Autowired
    CompanyService companyService;

    @Test
    public void 채용공고등록() throws Exception {
        // given
        CompanyRequestDto companyRequestDto = createCompanyRequestDto();
        Long savedCompanyId = companyService.save(companyRequestDto);

        RecruitRequestDto recruitRequestDto = createRecruitRequestDto(savedCompanyId);

        // when
        Long savedId = recruitService.registerRecruit(recruitRequestDto);

        // then
        assertEquals(savedId, recruitService.findById(savedId).getId());
    }

    @Test
    public void 채용공고삭제() throws Exception {
        // given
        CompanyRequestDto companyRequestDto = createCompanyRequestDto();
        Long savedCompanyId = companyService.save(companyRequestDto);

        RecruitRequestDto recruitRequestDto = createRecruitRequestDto(savedCompanyId);

        // when
        Long savedId = recruitService.registerRecruit(recruitRequestDto);
        recruitService.deleteRecruit(savedId);

        // then
        assertThrows(Exception.class, () -> recruitService.findById(savedId));
    }

    @Test
    public void 채용공고수정() throws Exception {
        // given
        CompanyRequestDto companyRequestDto = createCompanyRequestDto();
        Long savedCompanyId = companyService.save(companyRequestDto);

        RecruitRequestDto recruitRequestDto = createRecruitRequestDto(savedCompanyId);

        // when
        Long savedId = recruitService.registerRecruit(recruitRequestDto);
        RecruitUpdateDto recruitUpdateDto = new RecruitUpdateDto("개발자2", 20000L, "서울2", "스프링2");
        recruitService.updateRecruit(savedId, recruitUpdateDto);

        // then
        assertEquals(recruitUpdateDto.getPosition(), recruitService.findById(savedId).getPosition());
    }


    private CompanyRequestDto createCompanyRequestDto() {
        return new CompanyRequestDto("원티드", "한국", "분당");
    }

    private RecruitRequestDto createRecruitRequestDto(Long savedCompanyId) {
        return new RecruitRequestDto(savedCompanyId, "개발자", 10000L, "서울", "스프링");
    }
}