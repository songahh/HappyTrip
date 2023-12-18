package com.happytrip.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String name;
    private String email;
    private String img;
    private String refreshToken;
    private String accessToken;
}
