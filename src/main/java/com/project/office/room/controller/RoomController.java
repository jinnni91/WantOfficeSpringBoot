package com.project.office.room.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.project.office.room.dto.RoomDTO;
import com.project.office.room.service.RoomService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/room")
public class RoomController {

	private final RoomService roomService;
	
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}
	
	/* 1. 회의실 조회(회원) */
	@GetMapping("/rooms")
	public ResponseEntity<ResponseDTO> selectRoomList(@RequestParam(name= "page", defaultValue="1") int page){
		log.info("[RoomController] selectRoomList Start ===========");
		log.info("[RoomController] page : {}", page);
		
		Page<RoomDTO> roomDTOList = roomService.selectRoomList(page);
		
		PagingButton pageBtn = pagenation.getPagingButton(roomDTOList);
		
		log.info("[RoomController] pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(roomDTOList.getContent());
		
		log.info("[RoomController] selectRoomList End ===========");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	
	/* 2. 회의실 상세 조회(회원) */
	@GetMapping("/rooms/{roomNo}")
	public ResponseEntity<ResponseDTO> selectRoomDetail(@PathVariable Long roomNo) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "회의실 상세 정보 조회 성공", roomService.selectRoom(roomNo)));
	}
	
	/* 3. 회의실 등록(관리자) */
	@PostMapping("/room-managements")
	public ResponseEntity<ResponseDTO> insertRoomForAdmin(@ModelAttribute RoomDTO roomDTO){
		
		log.info("[RoomController] RoomDTO: {}", roomDTO);
		roomService.insertRoomForAdmin(roomDTO);
		
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회의실 등록 성공", roomDTO));
	}
	
	/* 4. 회의실 수정(관리자) */
	@PutMapping("/rooms-managements")
	public ResponseEntity<ResponseDTO> updateRoomForAdmin(@ModelAttribute RoomDTO roomDTO) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회의실 수정 성공", roomService.updateRoomForAdmin(roomDTO)));
	}
	
	/* 5. 회의실 삭제(관리자) */
	@DeleteMapping("/rooms-management/{roomNo}")
	public ResponseEntity<ResponseDTO> deleteRoomForAdmin(@PathVariable Long roomNo) {
		
		roomService.deleteRoomForAdmin(roomNo);
		
		return ResponseEntity
				.noContent()
				.build();
	}
	
	/* 6. 회의실 전체 목록 (관리자) */
	@GetMapping("/rooms-management")
	public ResponseEntity<ResponseDTO> selectRoomListForAdmin(@RequestParam(name="page", defaultValue="1")int page){
		log.info("[RoomController] selectRoomList Start ===========");
		log.info("[RoomController] page : {}", page);
		
		Page<RoomDTO> roomDTOList = roomService.selectRoomListForAdmin(page);
		
		PagingButton pageBtn = pagenation.getPagingButton(roomDTOList);
		
		log.info("[RoomController] pageBtn : {}", pageBtn);
		
		ResponseDTOWithPaging responseDTOWithPaging = new ResponseDTOWithPaging();
		responseDTOWithPaging.setPageBtn(pageBtn);
		responseDTOWithPaging.setData(roomDTOList.getContent());
		
		log.info("[RoomController] selectRoomList End ===========");
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", responseDTOWithPaging));
	}
	/* 7. 회의실 상세 조회 (관리자) */
	@GetMapping("/rooms-managements/{roomNo}")
	public ResponseEntity<ResponseDTO> selectRoomDetailForAdmin(@PathVariable Long roomNo) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDTO(HttpStatus.OK, "회의실 상세 정보 조회 성공", roomService.selectRoomForAdmin(roomNo)));
	}
}
