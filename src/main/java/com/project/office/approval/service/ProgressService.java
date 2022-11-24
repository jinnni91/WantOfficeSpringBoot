
package com.project.office.approval.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.office.approval.dto.ProgressDTO;
import com.project.office.approval.entity.Progress;
import com.project.office.approval.repository.ProgressRepository;

@Service 
public class ProgressService {
	
		private ProgressRepository progressRepository;
		private final ModelMapper modelMapper;		
		
		public ProgressService(ProgressRepository progressRepository, ModelMapper modelMapper) {
			this.modelMapper = modelMapper;
			this.progressRepository = progressRepository;
			
			
		}
		
		
		public ProgressDTO selectProduct(Long dpNo) {
			
			Progress progress = progressRepository.findByDpNo(dpNo).orElseThrow(() -> new IllegalArgumentException("작성 하신 결재가없습니다. DpNo=" + dpNo));
			ProgressDTO progressDto = modelMapper.map(progress, ProgressDTO.class);
			
		
			return progressDto;
		
	}
		
}