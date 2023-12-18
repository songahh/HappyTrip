//package com.happytrip.domain.user.controller;
//
//import com.happytrip.domain.user.dto.UserRequestDto;
//import com.happytrip.domain.user.exception.UserException;
//import com.happytrip.domain.user.dto.AuthResponseDto;
//import com.happytrip.domain.user.service.UserService;
//import com.happytrip.domain.auth.utils.JwtProvider;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//@Slf4j
//public class UserController {
//
//	private final UserService us;
//	private final JwtProvider jp;
//	private final AuthenticationManager am;
//
//	private final String success = "SUCCESS";
//
//	@ExceptionHandler(value = UserException.class)
//	public ResponseEntity<String> handleError(UserException e) {
//		return new ResponseEntity<>(e.getMessage(), e.getStatus());
//	}
//
////	@PostMapping("/google")
////	public ResponseEntity<?> login(
////			@RequestBody UserRequestDto userRequestDto) throws Exception {
////		log.info("******************** 구글 로그인 요청 : {} {}", userRequestDto.getEmail());
////		try{
////			am.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.getMemberId(), userRequestDto.getMemberPwd()));
////			log.info("******************** 구글 로그인 성공 : {}", userRequestDto.getMemberId());
////
////			String accessToken = jp.createAccessToken(userRequestDto.getMemberId());
////			String refreshToken = jp.createRefreshToken(userRequestDto.getMemberId());
////			AuthResponseDto memberResponseDto = AuthResponseDto.builder()
////					.accessToken(accessToken)
////					.refreshToken(refreshToken).build();
////			ms.signIn(userRequestDto.getMemberId(), refreshToken);
////			return new ResponseEntity<AuthResponseDto>(memberResponseDto, HttpStatus.OK);
////		} catch (Exception e){
////			e.printStackTrace();
////			throw new UserException("아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
////		}
////	}
//
////	@ApiOperation(value = "회원 정보", notes = "회원 정보를 반환합니다.")
////	@GetMapping("/info/{memberId}")
////	public ResponseEntity<?> getInfo(@PathVariable("memberId") String memberId) throws Exception{
////		log.info("******************** 회원 정보 요청 : {}", memberId);
////		AuthResponseDto memberResponseDto = ms.findUser(memberId);
////		return new ResponseEntity<AuthResponseDto>(memberResponseDto, HttpStatus.OK);
////	}
////
////
////	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급합니다.")
////	@PostMapping("/renew")
////	public ResponseEntity<?> renewAccessToken(@RequestBody Map<String, String> dto, HttpServletRequest request) throws Exception {
////		log.info("******************** access token 갱신 요청 : {}", dto.get("memberId"));
////		Map<String, Object> resultMap = new HashMap<>();
////		String token = request.getHeader("refreshToken");
////		log.debug("token : {}, memberDto : {}", token, memberDto);
////		if (jwtProvider.checkToken(token)) {
////			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
////				String accessToken = jwtProvider.createAccessToken(memberDto.getUserId());
////				log.debug("token : {}", accessToken);
////				log.debug("정상적으로 액세스토큰 재발급!!!");
////				resultMap.put("access-token", accessToken);
////				status = HttpStatus.CREATED;
////			}
////		} else {
////			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
////			status = HttpStatus.UNAUTHORIZED;
////		}
////		return new ResponseEntity<Map<String, Object>>(resultMap, status);
////	}
//
////	@ApiOperation(value = "회원 정보 수정", notes = "회원정보(이름, 이메일) 수정한다.")
////	@PatchMapping("/modify")
////	public ResponseEntity<?> modifyMember(@RequestBody UserRequestDto memberDto){
////		try{
////			memberService.modifyMember(memberDto);
////			return ResponseEntity.ok("SUCCESS");
////		} catch(Exception e){
////			return ResponseEntity.internalServerError().body("예상치 못한 문제가 발생했습니다.");
////		}
////
////	}
////
////	@ApiOperation(value = "회원 탈퇴", notes = "서비스에서 탈퇴한다.")
////	@DeleteMapping("/delete/{id}")
////	public ResponseEntity<?> deleteMember(@PathVariable String id){
////		try{
////			memberService.deleteMember(id);
////			return ResponseEntity.ok("SUCCESS");
////		} catch(Exception e){
////			return ResponseEntity.internalServerError().body("예상치 못한 문제가 발생했습니다.");
////		}
////	}
//}
