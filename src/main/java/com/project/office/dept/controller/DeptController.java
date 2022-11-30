package com.project.office.dept.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.common.ResponseDTO;
import com.project.office.dept.dto.DeptDTO;
import com.project.office.dept.service.DeptService;

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
	public ResponseEntity<ResponseDTO> deptList() {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 목록 조회 완료", deptService.deptList()));
	}
	
	// 부서 상세 조회
	@GetMapping("/list/{deptNo}")
	public ResponseEntity<ResponseDTO> selectDeptDetail(@PathVariable Long deptNo) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "부서 정보 조회 완료", deptService.selectDeptDetail(deptNo)));
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
