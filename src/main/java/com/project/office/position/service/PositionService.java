package com.project.office.position.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.dept.dto.DeptDTO;
import com.project.office.dept.entity.Dept;
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
	public Page<PositionDTO> positionList(int page) {
		log.info("[PositionService] positionList start ===========================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("positionNo").ascending());
		
		Page<Position> positionList = positionRepository.findAll(pageable);
		Page<PositionDTO> positionDtoList = positionList.map(position -> modelMapper.map(position, PositionDTO.class));
		
		log.info("[PositionService] positionDtoList : {}", positionDtoList.getContent());
		log.info("[PositionService] positionList End ===========================");
		
		return positionDtoList;
	}

	// 직위 삭제
	public void deletePosition(Long positionNo) {
		Position deletePosition = positionRepository.findById(positionNo).get();
		positionRepository.delete(deletePosition);		
	}
	
	
}
