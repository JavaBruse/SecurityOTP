package com.javabruse.repository;

import com.javabruse.domain.model.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPCodeRepository extends JpaRepository<OtpCode, String> {
}
