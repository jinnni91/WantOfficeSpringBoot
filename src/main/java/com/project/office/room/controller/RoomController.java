package com.project.office.room.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	/* 1. 회의실 조회 */
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
	
}
