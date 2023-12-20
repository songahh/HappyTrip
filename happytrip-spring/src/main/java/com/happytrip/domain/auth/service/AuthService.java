package com.happytrip.domain.auth.service;

import com.happytrip.domain.auth.dto.AuthRequestDto;
import com.happytrip.domain.auth.dto.AuthResponseDto;
import com.happytrip.domain.auth.exception.AuthException;
import com.happytrip.domain.user.entity.User;
import com.happytrip.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository ur;

    @Transactional
    public void updateRefreshToken(AuthRequestDto dto) {
        User user = ur.findByEmail(dto.getEmail()).orElseThrow(()->new AuthException("사용자가 없습니다.", HttpStatus.NOT_FOUND));
        user.setRefreshToken(dto.getRefreshToken());
    }
}
