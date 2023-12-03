package com.happytrip.domain.member.service;

import com.happytrip.domain.member.entity.Member;
import com.happytrip.domain.member.entity.MemberType;
import com.happytrip.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository mr;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = mr.findById(userId).get();
        ArrayList authorities = new ArrayList();
        log.debug("UserDetailService: {} {}", member.getMemberId(), member.getMemberPwd());
        return new User(member.getMemberId(), member.getMemberPwd(), authorities);
    }
}
