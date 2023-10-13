package preonboarding.wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.data.common.RegisterResponse;
import preonboarding.wanted.backend.data.recruit.RecruitDetailDto;
import preonboarding.wanted.backend.data.recruit.RecruitInfoDto;
import preonboarding.wanted.backend.data.recruit.RecruitRequestDto;
import preonboarding.wanted.backend.data.recruit.RecruitUpdateDto;
import preonboarding.wanted.backend.service.RecruitService;

import java.util.List;

@RestController
@RequestMapping("/api/recruit")
@RequiredArgsConstructor
@Slf4j
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping
    public RegisterResponse registerRecruit(@RequestBody RecruitRequestDto recruitRequestDto) {
        return new RegisterResponse(recruitService.registerRecruit(recruitRequestDto));
    }

    @GetMapping("/{id}")
    public RecruitDetailDto getRecruit(@PathVariable Long id) {
        return recruitService.findById(id);
    }

    @GetMapping
    public List<RecruitInfoDto> getRecruitList() {
        return recruitService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteRecruit(@PathVariable Long id) {
        recruitService.deleteRecruit(id);
    }

    @PutMapping("/{id}")
    public RecruitDetailDto updateRecruit(@PathVariable Long id, @RequestBody RecruitUpdateDto recruitUpdateDto) {
        recruitService.updateRecruit(id, recruitUpdateDto);
        return recruitService.findById(id);
    }

    @GetMapping("/search")
    public List<RecruitInfoDto> searchRecruit(@RequestParam String search) {
        log.info("search: {}", search);
        return recruitService.searchRecruit(search);
    }
}
