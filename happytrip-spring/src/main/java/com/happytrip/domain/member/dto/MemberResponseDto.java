package com.happytrip.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private String memberId;
    private String memberName;
    private String emailId;
    private String emailDomain;
    private String refreshToken;
    private String accessToken;
}
