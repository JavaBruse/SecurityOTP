package com.javabruse.controllers;

import com.javabruse.domain.dto.OtpRequest;
import com.javabruse.domain.dto.OtpValidationRequest;
import com.javabruse.service.OTPService;
import com.javabruse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final OTPService otpService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String exampleUser() {
        log.info("Test site");
        return "Hello, world!";
    }

    @PostMapping("/otp-gen")
    public String generateOtp(@RequestBody OtpRequest request) {
        log.info("Запрос на генерацию" + request);
        String otp = otpService.generateOtp(request);
        log.info("Завершено успешно: " + request);
        return otp;
    }

    @PostMapping("/otp-valid")
    public boolean validateOtp(@RequestBody OtpValidationRequest request) {
        log.info("Запрос на валидацию ОТР: " + request);
        boolean isValid = otpService.validateOtp(request);
        if (isValid) {
            log.info("ОТР верен: " + request);
        } else {
            log.warn("Ошибка верификации OTP: operationId={}", request);
        }
        return isValid;
    }


    @GetMapping("/get-admin")
    public void getAdminRole() {
        log.info("Попытка получения роли админ");
        service.getAdmin();
    }
}