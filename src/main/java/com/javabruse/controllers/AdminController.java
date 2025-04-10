package com.javabruse.controllers;

import com.javabruse.domain.dto.OtpOptionsRequest;
import com.javabruse.domain.model.OptionsOTP;
import com.javabruse.domain.model.User;
import com.javabruse.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        log.info("Test site");
        return "Hello, admin!";
    }


    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUSer() {
        log.info("Получение всех пользователей администратором");
        return service.getAllUsers();
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@RequestBody String name) {
        service.removeUser(name);
        log.info("Удаление пользователя: " + name);

    }

    @PostMapping("/editOptionsOTP")
    @PreAuthorize("hasRole('ADMIN')")
    public OtpOptionsRequest editOptionsOTP(@RequestBody OtpOptionsRequest optionsOTP) {
        log.info("Обновление OTP опций: " + optionsOTP);
        return service.setOptionsOTP(optionsOTP);
    }
}