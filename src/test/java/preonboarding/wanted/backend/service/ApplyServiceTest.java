package preonboarding.wanted.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.apply.ApplyRequestDto;
import preonboarding.wanted.backend.data.company.CompanyRequestDto;
import preonboarding.wanted.backend.data.recruit.RecruitRequestDto;
import preonboarding.wanted.backend.data.user.UserRequestDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class ApplyServiceTest {

    @Autowired
    ApplyService applyService;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    RecruitService recruitService;


    @Test
    public void 지원하기() throws Exception {
        // given
        UserRequestDto userRequestDto = createUserRequestDto();
        CompanyRequestDto companyRequestDto = createCompanyRequestDto();
        Long savedCompanyId = companyService.save(companyRequestDto);
        Long savedUserId = userService.registerUser(userRequestDto);

        RecruitRequestDto recruitRequestDto = createRecruitRequestDto(savedCompanyId);
        Long savedRecruitId = recruitService.registerRecruit(recruitRequestDto);

        ApplyRequestDto applyRequestDto = new ApplyRequestDto(savedUserId, savedRecruitId);

        // when
        Long savedId = applyService.apply(applyRequestDto);

        // then
        assertEquals(applyRequestDto.getUserId(), applyService.findById(savedId).getUserId());
    }

    @Test
    public void 중복지원() throws Exception {
        // given
        UserRequestDto userRequestDto = createUserRequestDto();
        CompanyRequestDto companyRequestDto = createCompanyRequestDto();
        Long savedCompanyId = companyService.save(companyRequestDto);
        Long savedUserId = userService.registerUser(userRequestDto);

        RecruitRequestDto recruitRequestDto = createRecruitRequestDto(savedCompanyId);
        Long savedRecruitId = recruitService.registerRecruit(recruitRequestDto);

        ApplyRequestDto applyRequestDto = new ApplyRequestDto(savedUserId, savedRecruitId);

        // when
        Long savedId = applyService.apply(applyRequestDto);

        // then
        assertThrows(Exception.class, () -> applyService.apply(applyRequestDto));
    }


    private UserRequestDto createUserRequestDto() {
        return new UserRequestDto("지원자 1");
    }

    private CompanyRequestDto createCompanyRequestDto() {
        return new CompanyRequestDto("원티드", "한국", "분당");
    }

    private RecruitRequestDto createRecruitRequestDto(Long companyId) {
        return new RecruitRequestDto(companyId, "백엔드", 3000L, "백엔드 개발자를 모집합니다.", "Java");
    }
}