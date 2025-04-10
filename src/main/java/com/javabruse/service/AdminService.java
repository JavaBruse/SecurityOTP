package com.javabruse.service;

import com.javabruse.domain.dto.OtpOptionsRequest;
import com.javabruse.domain.model.OptionsOTP;
import com.javabruse.domain.model.User;
import com.javabruse.repository.OTPCodeRepository;
import com.javabruse.repository.OTPOptionsRepository;
import com.javabruse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OTPOptionsRepository otpOptionsRepository;
    private final UserRepository userRepository;
    private final OTPCodeRepository otpCodeRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public OptionsOTP getOptionsOTP() {
        return otpOptionsRepository.getReferenceById(1l);
    }

    public OtpOptionsRequest setOptionsOTP(OtpOptionsRequest optionsOTP) {
        OptionsOTP otp = otpOptionsRepository.getReferenceById(1l);
        otp.setCountChars(optionsOTP.getCountChars());
        otp.setTimeLife(optionsOTP.getTimeLife());
        otpOptionsRepository.save(otp);
        return optionsOTP;
    }

    public void removeUser(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        if (user.isEmpty()) return;
        userRepository.delete(user.get());
    }
}
