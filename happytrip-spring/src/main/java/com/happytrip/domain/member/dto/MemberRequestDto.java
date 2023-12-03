package com.happytrip.domain.member.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class MemberRequestDto {

	private String memberId;
	private String memberName;
	private String memberPwd;
	private String emailId;
	private String emailDomain;
	
}
