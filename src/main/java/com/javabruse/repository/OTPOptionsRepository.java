package com.javabruse.repository;

import com.javabruse.domain.model.OptionsOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPOptionsRepository extends JpaRepository<OptionsOTP, Long> {
    OptionsOTP findTopByOrderByIdAsc();

}
