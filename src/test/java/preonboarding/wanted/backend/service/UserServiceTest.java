package preonboarding.wanted.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.user.UserRequestDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void 회원가입() throws Exception {
        // given
        UserRequestDto userRequestDto = new UserRequestDto("지원자 1");

        // when
        Long savedId = userService.registerUser(userRequestDto);

        // then
        assertEquals(userRequestDto.getName(), userService.findById(savedId).getName());
    }
}