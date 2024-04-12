package com.happytrip.domain.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class UserRequestDto {

	private String email;
	private String name;
	private String img;
	
}
