package preonboarding.wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RegisterResponse> apply(@RequestBody ApplyRequestDto applyRequestDto) {
        return ResponseEntity.ok().body(new RegisterResponse(applyService.apply(applyRequestDto)));
    }

    @GetMapping
    public ResponseEntity<List<ApplyInfoDto>> getApplyList() {
        return ResponseEntity.ok().body(applyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplyInfoDto> getApplyInfo(@PathVariable Long id) {
        return ResponseEntity.ok().body(applyService.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ApplyInfoDto>> getAppliesOfUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(applyService.findByUserId(id));
    }
}