package com.javabruse.controllers;

import com.javabruse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public String exampleUser() {
        return "Hello, world!";
    }

    @GetMapping("/get-admin")
    public void getAdminRole() {
        service.getAdmin();
    }
}