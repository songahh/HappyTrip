package com.happytrip.domain.member.service;

import com.happytrip.domain.member.exception.MemberException;
import com.happytrip.domain.member.entity.Member;
import com.happytrip.domain.member.entity.MemberType;
import com.happytrip.domain.member.dto.MemberRequestDto;
import com.happytrip.domain.member.dto.MemberResponseDto;
import com.happytrip.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;

    /**
     * 로그인
     **/
    @Override
    @Transactional
    public void signIn(String memberId, String refreshToken) throws Exception {
        // refreshToken 저장
        Member member = mr.findById(memberId).get();
        member.setRefreshToken(refreshToken);
    }

    /**
     * 회원가입
     **/
    @Override
    @Transactional
    public void signUp(MemberRequestDto memberDto) throws Exception {
        // 아이디 중복 체크 후 회원가입
        if(mr.findById(memberDto.getMemberId()).isPresent())
            throw new MemberException("이미 존재하는 아이디입니다.", HttpStatus.CONFLICT);
        Member member = Member.builder().memberId(memberDto.getMemberId()).memberName(memberDto.getMemberName())
                .memberPwd(memberDto.getMemberPwd()).memberType(MemberType.USER)
                .emailId(memberDto.getEmailId()).emailDomain(memberDto.getEmailDomain())
                .joinDate(new Timestamp(System.currentTimeMillis())).build();
        mr.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto findMember(String memberId) throws Exception {
        Member member = mr.findById(memberId).get();
        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .emailId(member.getEmailId())
                .emailDomain(member.getEmailDomain())
                .build();
        return memberResponseDto;
    }

}
