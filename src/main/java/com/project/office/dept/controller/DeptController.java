package com.project.office.dept.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.common.paging.PagingButton;
import com.project.office.common.paging.ResponseDTOWithPaging;
import com.project.office.common.paging.pagenation;
import com.project.office.dept.dto.DeptDTO;
import com.project.office.dept.service.DeptService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/dept")
public class DeptController {

	private final DeptService deptService;
	
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	// 부서 등록
	@PostMapping("/dept-management")
	public ResponseEntity<ResponseDTO> insertDept(@RequestBody DeptDTO deptDto) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 등록 완료", deptService.insertDept(deptDto)));
	}
	
	// 전체 부서 목록 조회
	@GetMapping("/list")
	public ResponseEntity<ResponseDTO> deptList(@RequestParam(name="page", defaultValue="1") int page) {
		log.info("[DeptController] deptList Start ================================");
		log.info("[DeptController] page : {}", page);
		
		Page<DeptDTO> deptDtoList = deptService.deptList(page);
		PagingButton pageBtn = pagenation.getPagingButton(deptDtoList);
		
		ResponseDTOWithPaging responseDtoWithPaging = new ResponseDTOWithPaging();
		responseDtoWithPaging.setPageBtn(pageBtn);
		responseDtoWithPaging.setData(deptDtoList.getContent());
		
		log.info("[DeptController] deptList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 목록 조회 완료", responseDtoWithPaging));
	}
	
	// 부서 수정
	@PutMapping("/dept-management")
	public ResponseEntity<ResponseDTO> updateDept(@RequestBody DeptDTO deptDto) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 수정 완료", deptService.updateDept(deptDto)));
	}
	
	// 부서 삭제
	@PutMapping("/delete")
	public ResponseEntity<ResponseDTO> deleteDept(@RequestBody DeptDTO deptDto) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 비활성화 완료", deptService.deleteDept(deptDto)));
	}
}
