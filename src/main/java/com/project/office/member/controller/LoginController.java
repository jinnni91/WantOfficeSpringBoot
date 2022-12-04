package com.project.office.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.service.MemberService;

@RestController
@RequestMapping("/account")
public class LoginController {

	private final MemberService memberService;
	
	public LoginController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDto) {
			
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인이 완료되었습니다.", memberService.login(memberDto)));
	}
	
	// 아이디 찾기
	@PostMapping("/find")
	public ResponseEntity<ResponseDTO> findId(@RequestBody MemberDTO memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "아이디 찾기에 성공하였습니다.", memberService.findId(memberDto)));
				
	}
}
