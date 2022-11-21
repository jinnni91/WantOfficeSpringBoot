package com.project.office.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	// 사원 등록
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@RequestBody MemberDTO memberDto) {
				
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "사원 등록이 완료되었습니다.", authService.signup(memberDto)));
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인이 완료되었습니다.", authService.login(memberDto)));
	}

}
