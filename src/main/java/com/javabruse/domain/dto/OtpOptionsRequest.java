package com.javabruse.domain.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpOptionsRequest {
    private Long timeLife;
    private int countChars;
}
