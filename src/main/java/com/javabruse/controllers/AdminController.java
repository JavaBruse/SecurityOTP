package com.javabruse.controllers;

import com.javabruse.domain.model.OptionsOTP;
import com.javabruse.domain.model.User;
import com.javabruse.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }


    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUSer() {
        return service.getAllUsers();
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@RequestBody String name) {
        service.removeUser(name);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public OptionsOTP editOptionsOTP(@RequestBody OptionsOTP optionsOTP) {
        return service.setOptionsOTP(optionsOTP);
    }
}