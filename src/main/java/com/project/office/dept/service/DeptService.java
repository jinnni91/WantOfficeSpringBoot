package com.project.office.dept.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.dept.dto.DeptDTO;
import com.project.office.dept.entity.Dept;
import com.project.office.dept.repository.DeptRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeptService {

	private final DeptRepository deptRepository;
	private final ModelMapper modelMapper;
	
	public DeptService(DeptRepository deptRepository, ModelMapper modelMapper) {
		this.deptRepository = deptRepository;
		this.modelMapper = modelMapper;
	}
	
	// 부서 등록
	@Transactional
	public DeptDTO insertDept(DeptDTO deptDto) {
		log.info("[DeptService] insertDept start ===========================");
		log.info("[DeptService] deptDto : {}", deptDto);
		
		deptRepository.save(modelMapper.map(deptDto, Dept.class));
		log.info("[DeptService] insertDept End ===========================");
		
		return deptDto;
	}
	
	// 전체 부서 목록 조회
	public List<DeptDTO> deptList() {
		
		List<Dept> dept = deptRepository.findAll();
		
		return dept.stream().map(d -> modelMapper.map(d, DeptDTO.class)).collect(Collectors.toList());

	}

	// 부서 수정
	public DeptDTO updateDept(DeptDTO deptDto) {
		log.info("[DeptService] updateDept start ===========================");
		log.info("[DeptService] deptDto : {}", deptDto);
		
		Dept foundDept = deptRepository.findById(deptDto.getDeptNo())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 부서입니다."));
		
		foundDept.update(deptDto.getDeptName());
		
		deptRepository.save(foundDept);
		
		log.info("[DeptService] updateDept End ===========================");
		return deptDto;
	}
	
	// 부서 삭제
	public DeptDTO deleteDept(DeptDTO deptDto) {
		log.info("[DeptService] deleteDept start ===========================");
		log.info("[DeptService] deptDto : {}", deptDto);
		
		Dept foundDept = deptRepository.findById(deptDto.getDeptNo())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 부서입니다."));
		
		foundDept.delete(deptDto.getDeptStatus());
		
		deptRepository.save(foundDept);
		
		log.info("[DeptService] deleteDept End ===========================");
		return deptDto;
	}

	// 부서 상세 조회
	public Object selectDeptDetail(Long deptNo) {
		
		DeptDTO deptDto = modelMapper.map(deptRepository.findById(deptNo)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 부서입니다.")), DeptDTO.class);
		return deptDto;
	}
	
}
