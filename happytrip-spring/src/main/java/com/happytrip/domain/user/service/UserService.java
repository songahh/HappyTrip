package com.happytrip.domain.user.service;

import com.happytrip.domain.user.dto.UserResponseDto;

public interface UserService {

	/** 로그인 **/
	void signIn(String memberId, String refreshToken) throws Exception;
	UserResponseDto findUser(String memberId) throws Exception;
//	void modifyMember(UserRequestDto memberDto) throws Exception;
//	void deleteMember(String memberId) throws Exception;
	/** 회원가입 **/
//	void signUp(UserRequestDto memberDto) throws Exception;
	
}
