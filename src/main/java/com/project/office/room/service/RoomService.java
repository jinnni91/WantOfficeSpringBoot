package com.project.office.room.service;

import java.io.IOException;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.room.dto.RoomDTO;
import com.project.office.room.entity.Room;
import com.project.office.room.repository.RoomRepository;
import com.project.office.util.FileUploadUtils;

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
	
	/* 1. 회의실 조회(공통) */
	public Page<RoomDTO> selectRoomList(int page) {
		
		log.info("[RoomService] selectRoomList Start =======");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("roomNo").descending());
		
		Page<Room> roomList = roomRepository.findAll(pageable);
		Page<RoomDTO> roomDTOList = roomList.map(room -> modelMapper.map(room, RoomDTO.class));
		
		roomDTOList.forEach(room -> room.setRoomFileUrl(IMAGE_URL + room.getRoomFileUrl()));
		
		log.info("[RoomService] roomDTOList : {}", roomDTOList.getContent());
		
		log.info("[RoomService] selectRoomList End =======");
		
		return roomDTOList;
	}

	/* 2. 회의실 상세 조회(공통) */
	public RoomDTO selectRoom(Long roomNo) {
		
		log.info("[RoomService] selectRoom start ===========");
		log.info("[RoomService] roomNo : {}", roomNo);
		
		Room room = roomRepository.findByRoomNo(roomNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 회의실이 없습니다. roomNo=" + roomNo));
		RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
		roomDTO.setRoomFileUrl(IMAGE_URL + roomDTO.getRoomFileUrl());
		
		log.info("[RoomService] roomDTO : " + roomDTO);
		
		log.info("[RoomService] selectRoom End ===========");
		
		return roomDTO;
	}
	
	/* 3. 회의실 등록 (관리자) */
	@Transactional
	public RoomDTO insertRoomForAdmin(RoomDTO roomDTO) {
		
		log.info("[RoomService] insertRoomForAdmin start ===========");
		log.info("[RoomService] roomDTO : {}", roomDTO);
		String imageName = UUID.randomUUID().toString().replace("-","");
		String replaceFileName = null;
		
		try {
		replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, roomDTO.getRoomImage());
		
		roomDTO.setRoomFileUrl(replaceFileName);
		
		log.info("[RoomService] replaceFileName : {}", replaceFileName);
		
		roomRepository.save(modelMapper.map(roomDTO, Room.class));
		
		}catch (IOException e){
			e.printStackTrace();
			try {
				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		log.info("[RoomService] insertRoomForAdmin start ===========");
		
		return roomDTO;
	}

	

	/* 4. 회의실 수정 (관리자) */
	
	@Transactional
	public RoomDTO updateRoomForAdmin(RoomDTO roomDTO) {
		log.info("[RoomService] updateRoomForAdmin start ===========");
		log.info("[RoomService] roomDTO : {}", roomDTO);
		
		String replaceFileName = null;
		
		try {
			Room oriRoom = roomRepository.findById(roomDTO.getRoomNo()).orElseThrow(
					() -> new IllegalArgumentException("해당 회의실이 없습니다. roomNo=" + roomDTO.getRoomNo()));
			String oriFile = oriRoom.getRoomFileUrl();
			
			if(roomDTO.getRoomImage() != null) {
				String fileName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, fileName, roomDTO.getRoomImage());
				roomDTO.setRoomFileUrl(replaceFileName);
				
				FileUploadUtils.deleteFile(IMAGE_DIR, oriFile);
				
			} else {
				roomDTO.setRoomFileUrl(oriFile);
			}
			
			oriRoom.update(roomDTO.getRoomName(),
					roomDTO.getRoomLocation(),
					roomDTO.getRoomCapacity(),
					roomDTO.getRoomFileUrl());
			
			roomRepository.save(oriRoom);
			
		}catch (IOException e) {
			e.printStackTrace();
			try {
				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			}catch (IOException e1) {
				e.printStackTrace();
			}
		}
		
		log.info("[RoomService] updateRoomForAdmin End ===========");
		return roomDTO;
	}
	/* 5. 회의실 삭제(관리자) */
	public void deleteRoomForAdmin(Long roomNo) {
		Room deleteRoom = roomRepository.findById(roomNo).get();
		roomRepository.delete(deleteRoom);
		
	}
	
	/* 6. 회의실 전체 목록 조회 (관리자) */
	public Page<RoomDTO> selectRoomListForAdmin(int page) {
		log.info("[RoomService] selectRoomList Start =======");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("roomNo").descending());
		
		Page<Room> roomList = roomRepository.findAll(pageable);
		Page<RoomDTO> roomDTOList = roomList.map(room -> modelMapper.map(room, RoomDTO.class));
		
		roomDTOList.forEach(room -> room.setRoomFileUrl(IMAGE_URL + room.getRoomFileUrl()));
		
		log.info("[RoomService] roomDTOList : {}", roomDTOList.getContent());
		
		log.info("[RoomService] selectRoomList End =======");
		
		return roomDTOList;
	}
	
	
	
	/* 7. 회의실 상세 조회 (관리자) */
	public RoomDTO selectRoomForAdmin(Long roomNo) {
		
		log.info("[RoomService] selectRoom start ===========");
		log.info("[RoomService] roomNo : {}", roomNo);
		
		Room room = roomRepository.findById(roomNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 회의실이 없습니다. roomNo=" + roomNo));
		RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
		roomDTO.setRoomFileUrl(IMAGE_URL + roomDTO.getRoomFileUrl());
		
		log.info("[RoomService] roomDTO : " + roomDTO);
		
		log.info("[RoomService] selectRoom End ===========");
		
		return roomDTO;
	}
	
}
