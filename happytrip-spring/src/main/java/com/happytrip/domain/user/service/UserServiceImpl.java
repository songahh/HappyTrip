package com.happytrip.domain.user.service;

import com.happytrip.domain.user.exception.UserException;
import com.happytrip.domain.user.entity.User;
import com.happytrip.domain.user.entity.UserType;
import com.happytrip.domain.user.dto.UserRequestDto;
import com.happytrip.domain.user.dto.UserResponseDto;
import com.happytrip.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository mr;

    /**
     * 로그인
     **/
    @Override
    @Transactional
    public void signIn(String userEmail, String refreshToken) throws Exception {
        // refreshToken 저장
        User user = mr.findByEmail(userEmail).get();
        user.setRefreshToken(refreshToken);
    }


    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findUser(String email) throws Exception {
        User user = mr.findByEmail(email).get();
        UserResponseDto memberResponseDto = UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .img(user.getImg())
                .build();
        return memberResponseDto;
    }

}
