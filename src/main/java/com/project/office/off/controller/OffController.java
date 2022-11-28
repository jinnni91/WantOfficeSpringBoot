package com.project.office.off.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;
import com.project.office.member.dto.MemberDTO;
import com.project.office.off.dto.OffDTO;
import com.project.office.off.service.OffService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class OffController {
	
	private final OffService offService;
	
	public OffController(OffService offService) {
		this.offService = offService;
	}
	
	/* 연차 조회 */
	@GetMapping("/off")
	public ResponseEntity<ResponseDTO> selectOff(@AuthenticationPrincipal MemberDTO member, @RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[OffController] selectOff Start ====================");
		log.info("[OffController] selectOff member : {}", member);
		log.info("[OffController] selectOff page : {}", page);
		
		Page<OffDTO> offDTOList = offService.getOffList(member, page);
		
		PagingButton pageBtn = pagenation.getPagingButton(offDTOList);
		log.info("[OffController] selectOff pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(offDTOList.getContent());
		
		log.info("[OffController] selectOff End ====================");
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "연차 신청 목록 조회 완료", responseDTOWithPaging));
		
	}
	
	/* 연차 신청 양식에 나타낼 결재권자 조회 */
	@GetMapping("/searchApp")
	public ResponseEntity<ResponseDTO> selectApp(@AuthenticationPrincipal MemberDTO member) {
		
		log.info("[OffController] selectApp Start ====================");
		log.info("[OffController] selectApp member : {}", member);
		
		log.info("[OffController] selectApp End ====================");
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "결재권자 이름 조회 완료", offService.selectApp(member)));
		
	}
	
	/* 연차 신청 */
	@PostMapping("/off")
	public ResponseEntity<ResponseDTO> insertOff(@AuthenticationPrincipal MemberDTO member) {
		
		log.info("[OffController] insertOff Start ====================");
		log.info("[OffController] insertOff member : {}", member);
		
		OffDTO offDTO = new OffDTO();
		
		offDTO.setMember(member);
		
		log.info("[OffController] insertOff End ====================");
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "연차 신청 완료", offService.insertOff(offDTO)));
		
	}
	
	/* 결과별 연차 신청 목록 조회(결재권자) */
	@GetMapping("/off/result")
	public ResponseEntity<ResponseDTO> selectOffForApp(@RequestParam(name="offResult") String offResult, @RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[OffController] selectOffForApp Start ====================");
		log.info("[OffController] selectOffForApp page : {}", page);
		log.info("[OffController] selectOffForApp offResult : {}", offResult);
		
		Page<OffDTO> offDTOList = offService.getOffListForApp(offResult, page);
		
		PagingButton pageBtn = pagenation.getPagingButton(offDTOList);
		log.info("[OffController] selectOff pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(offDTOList.getContent());
		
		log.info("[OffController] selectOffForApp End ====================");
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "대기 연차 신청 목록 조회 완료", responseDTOWithPaging));
		
	}
	
	/* 연차 상세 조회 */
	@GetMapping("/off/{offNo}")
	public ResponseEntity<ResponseDTO> selectOffDetail(@PathVariable Long offNo) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "연차 상세 조회 완료", offService.selectOff(offNo)));
		
	}

}
