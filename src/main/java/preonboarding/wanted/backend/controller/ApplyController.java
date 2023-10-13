package preonboarding.wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.data.apply.ApplyInfoDto;
import preonboarding.wanted.backend.data.apply.ApplyRequestDto;
import preonboarding.wanted.backend.data.common.RegisterResponse;
import preonboarding.wanted.backend.service.ApplyService;

import java.util.List;

@RestController
@RequestMapping("/api/apply")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping
    public RegisterResponse apply(@RequestBody ApplyRequestDto applyRequestDto) {
        return new RegisterResponse(applyService.apply(applyRequestDto));
    }

    @GetMapping
    public List<ApplyInfoDto> getApplyList() {
        return applyService.findAll();
    }

    @GetMapping("/{id}")
    public ApplyInfoDto getApplyInfo(@PathVariable Long id) {
        return applyService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<ApplyInfoDto> getAppliesOfUser(@PathVariable Long id) {
        return applyService.findByUserId(id);
    }
}