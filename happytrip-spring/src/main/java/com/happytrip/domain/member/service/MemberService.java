package com.happytrip.domain.member.service;

import com.happytrip.domain.member.dto.MemberRequestDto;
import com.happytrip.domain.member.dto.MemberResponseDto;

public interface MemberService {

	/** 로그인 **/
	void signIn(String memberId, String refreshToken) throws Exception;

	/** 회원가입 **/
	void signUp(MemberRequestDto memberDto) throws Exception;
	MemberResponseDto findMember(String memberId) throws Exception;
//	void modifyMember(MemberRequestDto memberDto) throws Exception;
//	void deleteMember(String memberId) throws Exception;
	
}
