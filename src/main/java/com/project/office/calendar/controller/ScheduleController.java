package com.project.office.calendar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.office.calendar.dto.ScheduleDTO;
import com.project.office.calendar.service.ScheduleService;
import com.project.office.common.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j   
@RestController
@RequestMapping("/api")
public class ScheduleController {
	
	private ScheduleService scheduleService;
	
	public ScheduleController ( ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@GetMapping("/calendar")
	public ResponseEntity<ResponseDTO> scheduleList (String scheduleSort) {
		
//		log.info("[ScheduleController] schedeuleSort : {} ", scheduleSort );
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", scheduleService.scheduleList(scheduleSort)));
	}
	
	@GetMapping("/calendar/{scheduleNo}")
	public ResponseEntity<ResponseDTO> selectSchedule (@PathVariable Long scheduleNo) {
		
//		log.info("[ScheduleController] scheduleNo : {} ", scheduleNo);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상세 조회 성공", scheduleService.Selectschedule(scheduleNo)));
	}
	
	@PostMapping("/calendar")
	public ResponseEntity<ResponseDTO> insertSchedule (@ModelAttribute ScheduleDTO scheduleDTO) {
		
//		log.info("[ScheduleController] scheduleDTO : {} ", scheduleDTO);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "일정 입력 성공", scheduleService.insertSchedule(scheduleDTO)));
	}
	
	@PutMapping("/calendar")
	public ResponseEntity<ResponseDTO> updateSchedule (@ModelAttribute ScheduleDTO scheduleDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "일정 수정 성공", scheduleService.updateSchedule(scheduleDTO)));
	}
	
//	@DeleteMapping("/calendar")
//	public ResponseEntity<ResponseDTO> deleteSchedule (Long scheduleNo) {
//		
//		scheduleService.deleteSchedule(scheduleNo);
//		
//		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "일정 삭제 성공", null));
//	}

}
