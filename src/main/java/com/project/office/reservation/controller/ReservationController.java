package com.project.office.reservation.controller;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.project.office.member.dto.MemberDTO;
import com.project.office.member.entity.Member;
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
	
	/* 1. 예약 조회(회원) */
	@GetMapping("/rvlist-management")
	public ResponseEntity<ResponseDTO> selectReservationMList(@RequestParam(name= "page", defaultValue="1")int page){

		
		Page<ReservationDTO> reservationDTOList = reservationService.selectReservationMList(page);
		
		PagingButton pageBtn = pagenation.getPagingButton(reservationDTOList);
		
		log.info("[ReservationController] pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(reservationDTOList.getContent());
		
		log.info("[ReservationController] selectReservationList End ==============");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 리스트 조회 성공", responseDTOWithPaging));
	}
	
//	/* 2. 예약 목록 조회 - 검색[예약중 / 예약 가능/ 예약 취소]*/
//	@GetMapping("/rvlists/search")
//	public ResponseEntity<ResponseDTO> selectSearchList(@RequestParam(name= "page", defaultValue="1")int page, @RequestParam(name="search") String reservationStatus){
//		log.info("[ReservationController] selectSearchList start ============ ");
//		log.info("[ReservationController] page: {} ", page);
//		log.info("[ReservationController] reservationStatus : {}", reservationStatus);
//		
//		Page<ReservationDTO> reservationDTOList = reservationService.selectReservationListByReservationStatus(page, reservationStatus);
//		
//		PagingButton pageBtn = pagenation.getPagingButton(reservationDTOList);
//		log.info("[ReservationController] pageBtn : {}", pageBtn);
//		
//		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
//		responseDTOWithPaging.setPageBtn(pageBtn);
//		responseDTOWithPaging.setData(reservationDTOList.getContent());
//		
//		log.info("[ReservationController] selectSearchList End ============ ");
//		
//		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 리스트 조회 성공", responseDTOWithPaging));
//	}
	
	/* 3. 예약조회(공통) */
	
	@GetMapping("/rvlist/{roomNo}")
	public ResponseEntity<ResponseDTO> selectReservationList(@PathVariable Long roomNo){

		log.info("[ReservationController] selectReservationList start=========");
		log.info("[ReservationController] room : {}", roomNo);
		
		//List<ReservationDTO> reservation = reservationService.selectReservationList(roomNo);
		
		log.info("[ReservationController] selectReservationList end=========");
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "예약 리스트 조회 성공", reservationService.selectReservationList(roomNo)));
	}
	
	
	/* 3-1. 예약 상세 조회(회원) */
	@GetMapping("/rvlists/{reservationNo}")
	public ResponseEntity<ResponseDTO> selectReservationDetail(@PathVariable Long reservationNo) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "예약 정보 상세 조회 성공", reservationService.selectReservationForAdmin(reservationNo)));
	}
	
	/* 4. 예약 등록 (회원) */
	
	@PostMapping("/rvlists-in/{roomNo}")
	public ResponseEntity<ResponseDTO> insertReservation(@AuthenticationPrincipal MemberDTO member, @RequestBody ReservationDTO reservationDTO, Long roomNo) {
		
		reservationDTO.setMember(member);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 등록 성공", reservationService.insertReservation(reservationDTO)));
	}
	
	
	/* 5. 예약 수정 (회원) */
	@PutMapping("/rvlists-managements")
	public ResponseEntity<ResponseDTO> updateReservation(@AuthenticationPrincipal MemberDTO member, @RequestBody ReservationDTO reservationDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 수정 성공", reservationService.updateReservation(member, reservationDTO)));
	}
	
	/* 6. 예약 목록 전체 조회(관리자) */
	@GetMapping("/rvlist-managements")
	public ResponseEntity<ResponseDTO> selectReservationListForAdmin(@RequestParam(name= "page", defaultValue="1")int page){
		log.info("[ReservationController] selectReservationList start ==============");
		log.info("[ReservationController] page : {}", page);
		
		Page<ReservationDTO> reservationDTOList = reservationService.selectReservationListForAdmin(page);
		
		PagingButton pageBtn = pagenation.getPagingButton(reservationDTOList);
		
		log.info("[ReservationController] pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(reservationDTOList.getContent());
		
		log.info("[ReservationController] selectReservationList End ==============");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "예약 리스트 조회 성공", responseDTOWithPaging));
	}
	
	/* 7. 예약 상세 조회(관리자) */
	@GetMapping("/rvlist-managements/{reservationNo}")
	public ResponseEntity<ResponseDTO> selectReservationDetailForAdmin(@PathVariable Long reservationNo) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "예약 정보 상세 조회 성공", reservationService.selectReservationForAdmin(reservationNo)));
	}
	
	/* 8. 예약 리스트 취소(삭제 - 관리자) */
	@DeleteMapping("/rvlist-managements/{reservationNo}")
	public ResponseEntity<ResponseDTO> deleteReservationForAdmin(@PathVariable Long reservationNo) {
		
		reservationService.deleteReservationForAdmin(reservationNo);
		
		return ResponseEntity
				.noContent()
				.build();
	}
}
