package com.example.web.controller;


import com.example.web.controller.request.UserJoinRequest;
import com.example.web.controller.request.UserLoginRequest;
import com.example.web.controller.response.UserJoinResponse;
import com.example.web.controller.response.UserLoginResponse;
import com.example.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestBody UserJoinRequest request) {
        return ResponseEntity.ok().body(UserJoinResponse.fromUser(userService.join(request.getName(), request.getPassword())));

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getName(), request.getPassword());
        return ResponseEntity.ok().body(new UserLoginResponse(token));
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(@RequestHeader(value="Authorization") String token) {
        System.out.println(token);
        return ResponseEntity.ok().body(userService.profile(token));
    }

}
