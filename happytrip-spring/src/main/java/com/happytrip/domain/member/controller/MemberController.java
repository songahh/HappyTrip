package com.happytrip.domain.member.controller;

import com.happytrip.domain.member.exception.MemberException;
import com.happytrip.domain.member.dto.MemberRequestDto;
import com.happytrip.domain.member.dto.MemberResponseDto;
import com.happytrip.domain.member.service.MemberService;
import com.happytrip.jwt.JwtProvider;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService ms;
	private final JwtProvider jp;
	private final AuthenticationManager am;

	private final String success = "SUCCESS";

	@ExceptionHandler(value = MemberException.class)
	public ResponseEntity<String> handleError(MemberException e) {
		return new ResponseEntity<>(e.getMessage(), e.getStatus());
	}

	@ApiOperation(value = "회원가입", notes = "서비스에 회원가입합니다.")
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody MemberRequestDto memberRequestDto) throws Exception {
		log.info("******************** 회원가입 요청 : {}", memberRequestDto);
		ms.signUp(memberRequestDto);
		log.info("******************** 회원가입 성공 : {}", memberRequestDto.getMemberId());
		return ResponseEntity.ok(success);
	}

	@ApiOperation(value = "로그인", notes = "서비스에 로그인합니다.")
	@PostMapping("/sign-in")
	public ResponseEntity<?> login(
			@RequestBody MemberRequestDto memberRequestDto) throws Exception {
		log.info("******************** 로그인 요청 : {} {}", memberRequestDto.getMemberId(), memberRequestDto.getMemberPwd());
		try{
			am.authenticate(new UsernamePasswordAuthenticationToken(memberRequestDto.getMemberId(), memberRequestDto.getMemberPwd()));
			log.info("******************** 로그인 성공 : {}", memberRequestDto.getMemberId());

			String accessToken = jp.createAccessToken(memberRequestDto.getMemberId());
			String refreshToken = jp.createRefreshToken(memberRequestDto.getMemberId());
			MemberResponseDto memberResponseDto = MemberResponseDto.builder()
					.accessToken(accessToken)
					.refreshToken(refreshToken).build();
			ms.signIn(memberRequestDto.getMemberId(), refreshToken);
			return new ResponseEntity<MemberResponseDto>(memberResponseDto, HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();
			throw new MemberException("아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value = "회원 정보", notes = "회원 정보를 반환합니다.")
	@GetMapping("/info/{memberId}")
	public ResponseEntity<?> getInfo(@PathVariable("memberId") String memberId) throws Exception{
		log.info("******************** 회원 정보 요청 : {}", memberId);
		MemberResponseDto memberResponseDto = ms.findMember(memberId);
		return new ResponseEntity<MemberResponseDto>(memberResponseDto, HttpStatus.OK);
	}


//	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급합니다.")
//	@PostMapping("/renew")
//	public ResponseEntity<?> renewAccessToken(@RequestBody Map<String, String> dto) throws Exception {
//		log.info("******************** access token 갱신 요청 : {}", dto.get("memberId"));
//		Map<String, Object> resultMap = new HashMap<>();
//		String token = request.getHeader("refreshToken");
//		log.debug("token : {}, memberDto : {}", token, memberDto);
//		if (jwtProvider.checkToken(token)) {
//			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
//				String accessToken = jwtProvider.createAccessToken(memberDto.getUserId());
//				log.debug("token : {}", accessToken);
//				log.debug("정상적으로 액세스토큰 재발급!!!");
//				resultMap.put("access-token", accessToken);
//				status = HttpStatus.CREATED;
//			}
//		} else {
//			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
//			status = HttpStatus.UNAUTHORIZED;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}

//	@ApiOperation(value = "회원 정보 수정", notes = "회원정보(이름, 이메일) 수정한다.")
//	@PatchMapping("/modify")
//	public ResponseEntity<?> modifyMember(@RequestBody MemberRequestDto memberDto){
//		try{
//			memberService.modifyMember(memberDto);
//			return ResponseEntity.ok("SUCCESS");
//		} catch(Exception e){
//			return ResponseEntity.internalServerError().body("예상치 못한 문제가 발생했습니다.");
//		}
//
//	}
//
//	@ApiOperation(value = "회원 탈퇴", notes = "서비스에서 탈퇴한다.")
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteMember(@PathVariable String id){
//		try{
//			memberService.deleteMember(id);
//			return ResponseEntity.ok("SUCCESS");
//		} catch(Exception e){
//			return ResponseEntity.internalServerError().body("예상치 못한 문제가 발생했습니다.");
//		}
//	}
}
