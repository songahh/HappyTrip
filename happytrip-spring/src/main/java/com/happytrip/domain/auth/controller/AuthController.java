package com.happytrip.domain.auth.controller;

import com.happytrip.domain.auth.dto.AuthRequestDto;
import com.happytrip.domain.auth.dto.AuthResponseDto;
import com.happytrip.domain.auth.exception.AuthException;
import com.happytrip.domain.auth.service.AuthService;
import com.happytrip.domain.user.exception.UserException;
import com.happytrip.domain.user.service.UserService;
import com.happytrip.domain.auth.utils.JwtProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
	private final JwtProvider jp;
	private final AuthService as;

	private final String success = "SUCCESS";

	@ExceptionHandler(value = AuthException.class)
	public ResponseEntity<String> handleError(UserException e) {
		return new ResponseEntity<>(e.getMessage(), e.getStatus());
	}

//	@GetMapping("/register/success")
//	public void redirectToFront(@AuthenticationPrincipal OAuth2User oAuth2User, HttpServletResponse response) throws IOException {
//		response.sendRedirect("http://localhost:5173/user/login");
//	}

	@GetMapping("/register/success")
	public ResponseEntity<AuthResponseDto> login(@AuthenticationPrincipal OAuth2User oAuth2User){
		log.info("*******************login");
//		OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
//		OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
		Map<String, Object> attributes = oAuth2User.getAttributes();

		String name = (String) attributes.get("name");
		String picture = (String) attributes.get("picture");
		String email = (String) attributes.get("email");

		String accessToken = jp.createAccessToken(email);
		String refreshToken = jp.createRefreshToken(email);

		as.updateRefreshToken(AuthRequestDto.builder().email(email).refreshToken(refreshToken).build());
		AuthResponseDto authResponseDto = AuthResponseDto.builder()
				.name(name).img(picture).email(email)
				.accessToken(accessToken)
				.refreshToken(refreshToken).build();
		return ResponseEntity.ok(authResponseDto);
	}

}
