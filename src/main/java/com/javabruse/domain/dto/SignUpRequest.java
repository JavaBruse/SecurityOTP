package com.javabruse.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String telegram;
    private String phone;

    @Override
    public String toString() {
        return "Пользователь: {" +
                "username='" + username  +
                ", telegram='" + telegram  +
                ", phone='" + phone +
                ", email='" + email +
                '}';
    }
}