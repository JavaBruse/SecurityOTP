package com.javabruse.service;

import com.javabruse.repository.OTPCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OTPService {
    private final OTPCodeRepository otpCodeRepository;

}
