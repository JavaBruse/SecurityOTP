package com.javabruse.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "otp_code", schema = "security_schema")
public class OtpCode {

    @Id
    @Column(name = "id_user")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "otp_code", nullable = false)
    private String otpCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OtpType type;

    @Column(name = "date_at", nullable = false)
    private Long dateAt;

    @Column(name = "date_die", nullable = false)
    private Long dateDie;
}
