package preonboarding.wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.user.User;
import preonboarding.wanted.backend.data.user.UserRequestDto;
import preonboarding.wanted.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public Long registerUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getName());
        return userRepository.save(user).getId();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
