package com.project.office.position.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;
import com.project.office.position.dto.PositionDTO;
import com.project.office.position.service.PositionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/position")
public class PositionController {

	private final PositionService positionService;
	
	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}
	
	// 직위 등록
	@PostMapping("/position-management")
	public ResponseEntity<ResponseDTO> insertPosition(@RequestBody PositionDTO positionDto) {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직위 등록 완료", positionService.insertPosition(positionDto)));
	}
	
	// 전체 직위 목록 조회
	@GetMapping("/list")
	public ResponseEntity<ResponseDTO> positionList(@RequestParam(name="page", defaultValue="1") int page) {
		log.info("[PositionController] positionList Start ================================");
		log.info("[PositionController] page : {}", page);
		
		Page<PositionDTO> positionDtoList = positionService.positionList(page);
		PagingButton pageBtn = pagenation.getPagingButton(positionDtoList);
		
		ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
		responseDtoWithPaging.setPageBtn(pageBtn);
		responseDtoWithPaging.setData(positionDtoList.getContent());
		
		log.info("[PositionController] positionList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "직위 목록 조회 완료", responseDtoWithPaging));
	}
	
	// 직위 삭제
	@DeleteMapping("/position-management/{positionNo}")
	public ResponseEntity<ResponseDTO> deletePosition(@PathVariable Long positionNo) {
		positionService.deletePosition(positionNo);
		return ResponseEntity.noContent().build();
	}
}
