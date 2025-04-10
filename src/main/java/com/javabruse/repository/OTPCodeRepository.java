package com.javabruse.repository;

import com.javabruse.domain.model.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findByOperationId(String operationId);
}
