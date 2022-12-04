package com.project.office.member.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.service.AuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	// 사원 등록
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@ModelAttribute MemberDTO memberDto) {
		
		authService.signup(memberDto);
		memberDto.setMemberImage(null);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "사원 등록이 완료되었습니다.", memberDto));
	}
	
	// 전체 사원 조회 (관리자 제외)
	@GetMapping("/member")
	public ResponseEntity<ResponseDTO> memberInfoList(@AuthenticationPrincipal MemberDTO memberDto, @RequestParam(name="page", defaultValue="1") int page) {
		log.info("[AuthController] allMemberInfo Start ================================");
		log.info("[AuthController] page : {}", page);
		
		Page<MemberDTO> memberDtoList = authService.memberInfoList(memberDto, page);
		
		PagingButton pageBtn = pagenation.getPagingButton(memberDtoList);
		
		log.info("[AuthController] pageInfo : {}", pageBtn);
		
		ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
		responseDtoWithPaging.setPageBtn(pageBtn);
		responseDtoWithPaging.setData(memberDtoList.getContent());
		
		log.info("[AuthController] allMemberInfo End ================================");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 사원 조회가 완료되었습니다.", responseDtoWithPaging));
	}
	
	// 사원 상세 조회
	@GetMapping("/member/{memberNo}")
	public ResponseEntity<ResponseDTO> selectMemberInfoDetail(@PathVariable Long memberNo) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", authService.selectMemberInfoDetail(memberNo)));
	}
	
	// 사원 정보 수정 (관리자)
	@PatchMapping("/member/update")
	public ResponseEntity<ResponseDTO> updateMember(@ModelAttribute MemberDTO memberDto) {
		
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 정보 수정 완료", authService.updateMember(memberDto)));
	}
	
	// 사원 비활성화
	@PatchMapping("/member/delete/{memberNo}")
	public ResponseEntity<ResponseDTO> deleteMember(@PathVariable Long memberNo, @ModelAttribute MemberDTO memberDto) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사원 비활성화 완료", authService.deleteMember(memberDto, memberNo)));		
	}
	
}
