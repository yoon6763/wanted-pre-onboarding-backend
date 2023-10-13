package preonboarding.wanted.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.company.CompanyRequestDto;
import preonboarding.wanted.backend.data.company.CompanyUpdateDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    public void 회사등록() throws Exception {
        // given
        CompanyRequestDto companyRequestDto = new CompanyRequestDto("원티드", "한국", "분당");

        // when
        Long savedId = companyService.save(companyRequestDto);

        // then
        assertEquals(companyRequestDto.getName(), companyService.findById(savedId).getName());
    }

    @Test
    public void 회사수정() throws Exception {
        // given
        CompanyRequestDto companyRequestDto = new CompanyRequestDto("원티드", "한국", "분당");
        Long savedId = companyService.save(companyRequestDto);

        // when
        CompanyUpdateDto companyUpdateDto = new CompanyUpdateDto("원티드2", "한국2", "분당2");
        companyService.update(savedId, companyUpdateDto);

        // then
        assertEquals(companyUpdateDto.getName(), companyService.findById(savedId).getName());
    }

    @Test
    public void 회사삭제() throws Exception {
        // given
        CompanyRequestDto companyRequestDto = new CompanyRequestDto("원티드", "한국", "분당");
        Long savedId = companyService.save(companyRequestDto);

        // when
        companyService.delete(savedId);

        // then
        assertThrows(Exception.class, () -> companyService.findById(savedId));
    }

}