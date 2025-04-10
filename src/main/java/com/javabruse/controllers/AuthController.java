package com.javabruse.controllers;

import com.javabruse.domain.dto.JwtAuthenticationResponse;
import com.javabruse.domain.dto.SignInRequest;
import com.javabruse.domain.dto.SignUpRequest;
import com.javabruse.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpRequest request) {
        authenticationService.signUp(request);
        log.info(request + " пользователь зарегестрирован");
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        JwtAuthenticationResponse jwt = authenticationService.signIn(request);
        log.info(request.getUsername() + " пользователь авторизован");
        return jwt;
    }

}

