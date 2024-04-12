package com.happytrip.domain.auth.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {

	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.access-token.expiretime}")
	private long accessTokenExpireTime;

	@Value("${jwt.refresh-token.expiretime}")
	private long refreshTokenExpireTime;

	/**	Token 발급 **/
	public String createAccessToken(String memberId) {
		return create(memberId, "access-token", accessTokenExpireTime);
	}
	public String createRefreshToken(String memberId) {
		return create(memberId, "refresh-token", refreshTokenExpireTime);
	}
	private String create(String memberId, String subject, long expireTime) {
/**		Payload 설정 : 생성일 (IssuedAt), 유효기간 (Expiration), 토큰 제목 (Subject) **/
		Claims claims = Jwts.claims()
				.setSubject(subject) // 토큰 제목 설정 ex) access-token, refresh-token
				.setIssuedAt(new Date()) // 생성일 설정
				.setExpiration(new Date(System.currentTimeMillis() + expireTime)); // 만료일 설정 (유효기간)

/**		저장할 data의 key, value 넣기 **/
		claims.put("memberId", memberId);
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT").setClaims(claims) // Header: 토큰 타입, 해쉬 알고리즘 정보
				.signWith(SignatureAlgorithm.HS256, this.generateKey())     // Signature: secret key를 활용한 암호화
				.compact(); // 직렬화 처리.
		return jwt;
	}

	//	Signature 설정에 들어갈 key 생성
	private byte[] generateKey() {
		byte[] key = null;
		try {
//			charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨
			key = salt.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (log.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				log.error("JWT Key 생성 Error : {}", e.getMessage());
			}
		}
		return key;
	}

	/**	Token 확인 **/
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String extractUsername(String token) {
		final Claims claims = extractAllClaims(token);
		log.debug("extractUsername {}", claims.get("memberId"));
		return (String) claims.get("memberId");
		//return extractClaim(token, Claims::getSubject);
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
//			Json Web Signature? 서버에서 인증을 근거로 인증정보를 서버의 private key로 서명 한것을 토큰화 한것
//			setSigningKey : JWS 서명 검증을 위한  secret key 세팅
//			parseClaimsJws : 파싱하여 원본 jws 만들기
		return Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody();
	}

}
