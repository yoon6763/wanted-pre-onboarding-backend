package preonboarding.wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.data.recruit.RecruitInfoDto;
import preonboarding.wanted.backend.data.recruit.RecruitRequestDto;
import preonboarding.wanted.backend.service.RecruitService;

@RestController
@RequestMapping("/api/recruit")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping
    public Long registerRecruit(@RequestBody RecruitRequestDto recruitRequestDto) {
        return recruitService.registerRecruit(recruitRequestDto);
    }

    @GetMapping("/{id}")
    public RecruitInfoDto getRecruit(@PathVariable Long id) {
        return recruitService.findById(id);
    }
}
