package com.project.office.room.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import com.project.office.room.dto.RoomDTO;
import com.project.office.room.entity.Room;
import com.project.office.room.repository.RoomRepository;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RoomService {

	private final RoomRepository roomRepository;
	private final ModelMapper modelMapper;
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	@Value("${image.image-url}")
	private String IMAGE_URL;
	
	public RoomService(RoomRepository roomRepository, ModelMapper modelMapper) {
		this.roomRepository = roomRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 1. 회의실 조회 */
	public Page<RoomDTO> selectRoomList(int page) {
		
		log.info("[RoomService] selectRoomList Start =======");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("roomNo").descending());
		
		Page<Room> roomList = roomRepository.findByRoomLocation(pageable, "Y");
		Page<RoomDTO> roomDTOList = roomList.map(room -> modelMapper.map(room, RoomDTO.class));
		
		roomDTOList.forEach(room -> room.setRoomImageUrl(IMAGE_URL + room.getRoomImageUrl()));
		
		log.info("[RoomService] roomDTOList : {}", roomDTOList.getContent());
		
		log.info("[RoomService] selectRoomList Start =======");
		
		return roomDTOList;
	}

}
