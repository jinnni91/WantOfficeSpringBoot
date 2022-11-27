package com.project.office.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.service.MemberService;

@RestController
@RequestMapping("/api/my")
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 내 정보 조회
	@GetMapping("/member/{memberNo}")
	public ResponseEntity<ResponseDTO> selectMyInfo(@PathVariable Long memberNo) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내 정보 조회 성공", memberService.selectMyInfo(memberNo)));
	}
	
	// 내 정보 수정
	@PutMapping("/member/{memberNo}")
	public ResponseEntity<ResponseDTO> updateMyInfo(@ModelAttribute MemberDTO memberDto, @PathVariable Long memberNo) {
		memberDto.setMemberNo(memberNo);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내 정보 수정 성공", memberService.updateMyInfo(memberDto)));
	}
	
	
}
