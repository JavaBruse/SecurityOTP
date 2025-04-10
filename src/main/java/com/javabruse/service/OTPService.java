package com.javabruse.service;

import com.javabruse.controllers.UserController;
import com.javabruse.domain.dto.OtpRequest;
import com.javabruse.domain.dto.OtpValidationRequest;
import com.javabruse.domain.model.OptionsOTP;
import com.javabruse.domain.model.OtpCode;
import com.javabruse.domain.model.OtpState;
import com.javabruse.domain.model.User;
import com.javabruse.repository.OTPCodeRepository;
import com.javabruse.repository.OTPOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OTPService {
    private final OTPCodeRepository otpCodeRepository;
    private final OTPOptionsRepository otpOptionsRepository;
    private final EmailService emailService;
    private final SmppService smppService;
    private final UserService userService;
    private final TelegramService telegramService;
    private static final Logger log = LoggerFactory.getLogger(OTPService.class);

    public String generateOtp(OtpRequest request) {
        OptionsOTP config = otpOptionsRepository.findTopByOrderByIdAsc();
        int length = config != null ? config.getCountChars() : 6;
        String code = generateCode(length);

        User currentUser = getUser();

        OtpCode otp = OtpCode.builder()
                .code(code)
                .operationId(request.getOperationId())
                .createdAt(LocalDateTime.now())
                .user(currentUser)
                .state(OtpState.ACTIVE)
                .build();
        log.info(otp.getState().toString() + " " + otp);
        otpCodeRepository.save(otp);
        switch (request.getDeliveryType().toUpperCase()) {
            case "FILE" -> saveToFile(currentUser.getUsername(), code);
            case "EMAIL" -> emailService.sendEmail(currentUser, code);
            case "SMS" -> smppService.sendSms(currentUser.getPhoneNumber(), code);
            case "TELEGRAM" -> telegramService.sendCode(currentUser.getTelegram(), code);
            default -> throw new RuntimeException("Неподдерживаемый тип доставки");
        }
        return code;
    }

    public boolean validateOtp(OtpValidationRequest request) {
        Optional<OtpCode> otpOptional = otpCodeRepository.findByOperationId(request.getOperationId());
        if (otpOptional.isEmpty()) return false;

        if (otpOptional.get().getState() != OtpState.ACTIVE) return false;

        OtpCode otp = otpOptional.get();
        otp.setState(OtpState.USED);
        otpCodeRepository.save(otp);
        return otp.getCode().equals(request.getCode());
    }

    private String generateCode(int length) {
        String dict = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890"; //строка содержит все доступные символы
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = randomMinMax(0, dict.length() - 1);
            sb.append(dict.substring(number, number + 1));
        }
        return sb.toString();
    }

    private static int randomMinMax(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private void saveToFile(String username, String code) {
        try (FileWriter writer = new FileWriter("otp_" + username + ".txt")) {
            writer.write("OTP код: " + code);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи OTP в файл");
        }
    }

    private User getUser() {
        String userName = org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userService.getByUsername(userName);
    }
}
