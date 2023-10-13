package preonboarding.wanted.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import preonboarding.wanted.backend.data.common.RegisterResponse;
import preonboarding.wanted.backend.data.user.UserRequestDto;
import preonboarding.wanted.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(new RegisterResponse(userService.registerUser(userRequestDto)));
    }
}
