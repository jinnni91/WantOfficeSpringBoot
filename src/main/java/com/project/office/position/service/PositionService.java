package com.project.office.position.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.office.position.dto.PositionDTO;
import com.project.office.position.entity.Position;
import com.project.office.position.repository.PositionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PositionService {

	private final PositionRepository positionRepository;
	private final ModelMapper modelMapper;
	
	public PositionService(PositionRepository positionRepository, ModelMapper modelMapper) {
		this.positionRepository = positionRepository;
		this.modelMapper = modelMapper;
	}
	
	// 직위 등록
	@Transactional
	public PositionDTO insertPosition(PositionDTO positionDto) {
		log.info("[PositionService] insertPosition start ===========================");
		log.info("[PositionService] positionDto : {}", positionDto);
		
		positionRepository.save(modelMapper.map(positionDto, Position.class));
		log.info("[PositionService] insertPosition End ===========================");
				
		return positionDto;
	}

	// 전체 직위 목록 조회
	public List<PositionDTO> positionList() {

		List<Position> position = positionRepository.findAll();
		
		return position.stream().map(p -> modelMapper.map(p, PositionDTO.class)).collect(Collectors.toList());
	}

	// 직위 삭제
	public void deletePosition(Long positionNo) {
		Position deletePosition = positionRepository.findById(positionNo).get();
		positionRepository.delete(deletePosition);		
	}
	
	
}
