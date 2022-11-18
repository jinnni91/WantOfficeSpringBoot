package com.project.office.attendance.controller;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.attendance.dto.AttendanceDTO;
import com.project.office.attendance.service.AttendanceService;
import com.project.office.common.ResponseDTO;
import com.project.office.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
	
	private final AttendanceService attendanceService;
	
	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	@PostMapping("/in")
	public ResponseEntity<ResponseDTO> insertAttIn(@RequestBody AttendanceDTO attendanceDTO,
			@AuthenticationPrincipal MemberDTO member) throws ParseException {
		
		attendanceDTO.setMember(member);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "출근 완료", attendanceService.insertAttIn(attendanceDTO)));
		
	}
	
	@PostMapping("/out")
	public ResponseEntity<ResponseDTO> insertAttOut(@RequestBody AttendanceDTO attendanceDTO,
			@AuthenticationPrincipal MemberDTO member) throws ParseException {
		
		attendanceDTO.setMember(member);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "퇴근 완료", attendanceService.insertAttOut(attendanceDTO)));
		
	}

}
