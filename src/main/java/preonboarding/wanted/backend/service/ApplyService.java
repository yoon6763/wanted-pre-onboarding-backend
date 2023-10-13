package preonboarding.wanted.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.data.apply.Apply;
import preonboarding.wanted.backend.data.apply.ApplyInfoDto;
import preonboarding.wanted.backend.data.apply.ApplyRequestDto;
import preonboarding.wanted.backend.data.recruit.Recruit;
import preonboarding.wanted.backend.data.user.User;
import preonboarding.wanted.backend.exception.ApplyNotFoundException;
import preonboarding.wanted.backend.exception.DuplicateApplyException;
import preonboarding.wanted.backend.exception.RecruitNotFoundException;
import preonboarding.wanted.backend.exception.UserNotFoundException;
import preonboarding.wanted.backend.repository.ApplyRepository;
import preonboarding.wanted.backend.repository.RecruitRepository;
import preonboarding.wanted.backend.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;

    @Transactional
    public Long apply(ApplyRequestDto applyRequestDto) {
        User user = userRepository.findById(applyRequestDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Recruit recruit = recruitRepository.findById(applyRequestDto.getRecruitId()).orElseThrow(RecruitNotFoundException::new);

        checkDuplicateApply(user, recruit);

        Apply apply = Apply.apply(recruit, user);
        return applyRepository.save(apply).getId();
    }

    public List<ApplyInfoDto> findAll() {
        return applyRepository.findAll().stream().map(Apply::toInfoDto).toList();
    }

    public ApplyInfoDto findById(Long id) {
        return applyRepository.findById(id).orElseThrow(ApplyNotFoundException::new).toInfoDto();
    }

    public List<ApplyInfoDto> findByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return applyRepository.findByUser(user).stream().map(Apply::toInfoDto).toList();
    }

    private void checkDuplicateApply(User user, Recruit recruit) {
        applyRepository.findByUserAndRecruit(user, recruit).ifPresent(apply -> {
            throw new DuplicateApplyException();
        });
    }
}
