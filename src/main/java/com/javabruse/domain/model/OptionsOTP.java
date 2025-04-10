package com.javabruse.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "options_otp", schema = "security_schema")
public class OptionsOTP {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "time_life", nullable = false)
    private Long timeLife;

    @Column(name = "count_chars", nullable = false)
    private int countChars;

    @Override
    public String toString() {
        return "Опции ОТП {" + ", timeLife=" + timeLife + ", countChars=" + countChars + '}';
    }
}
