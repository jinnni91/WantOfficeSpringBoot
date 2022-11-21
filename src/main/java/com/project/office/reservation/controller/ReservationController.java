package com.project.office.reservation.controller;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.project.office.reservation.dto.ReservationDTO;
import com.project.office.reservation.service.ReservationService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/room")
public class ReservationController {

	private final ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	/* 1. 예약 전체 조회(회원) */
	@GetMapping("/rvlists")
	public ResponseEntity<ResponseDTO> selectReservationList(@RequestParam(name= "page", defaultValue="1")int page){
		log.info("[ReservationController] selectReservationList start ==============");
		log.info("[ReservationController] page : {}", page);
		
		Page<ReservationDTO> reservationDTOList = reservationService.selectReservationList(page);
		
		PagingButton pageBtn = pagenation.getPagingButton(reservationDTOList);
		
		log.info("[ReservationController] pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(reservationDTOList.getContent());
		
		log.info("[ReservationController] selectReservationList End ==============");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 리스트 조회 성공", responseDTOWithPaging));
	}
	
	/* 1-1. 예약 목록 조회 - 검색[예약중 / 예약 가능/ 예약 취소]*/
	@GetMapping("/rvlists/search")
	public ResponseEntity<ResponseDTO> selectSearchList(@RequestParam(name= "page", defaultValue="1")int page, @RequestParam(name="search") String reservationStatus){
		log.info("[ReservationController] selectSearchList start ============ ");
		log.info("[ReservationController] page: {} ", page);
		log.info("[ReservationController] reservationDate : {}", reservationStatus);
		
		Page<ReservationDTO> reservationDTOList = reservationService.selectReservationListByReservationStatus(page, reservationStatus);
		
		PagingButton pageBtn = pagenation.getPagingButton(reservationDTOList);
		log.info("[ReservationController] pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(reservationDTOList.getContent());
		
		log.info("[ReservationController] selectSearchList End ============ ");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 리스트 조회 성공", responseDTOWithPaging));
	}
	
	/* 2. 예약 상세 조회(공통) */
	@GetMapping("/rvlists/{reservationNo}")
	public ResponseEntity<ResponseDTO> selectReservationDetail(@PathVariable Long reservationNo) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "예약 정보 상세 조회 성공", reservationService.selectReservation(reservationNo)));
	}
	
	/* 3. 예약 등록 (고객) */
	@PostMapping("/rvlists")
	public ResponseEntity<ResponseDTO> insertReservation(@ModelAttribute ReservationDTO reservationDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 등록 성공", reservationService.insertReservation(reservationDTO)));
	}
	
	/* 4. 예약 수정 (회원) */
	@PutMapping("/rvlists")
	public ResponseEntity<ResponseDTO> updateReservation(@ModelAttribute ReservationDTO reservationDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 수정 성공", reservationService.updateReservation(reservationDTO)));
	}
	
}
