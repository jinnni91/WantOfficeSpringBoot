//
//package com.project.office.approval.service;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import com.project.office.approval.dto.ProgressDTO;
//import com.project.office.approval.entity.Progress;
//import com.project.office.approval.repository.ProgressRepository;
//
//@Service 
//public class ProgressService {
//	
//		private ProgressRepository progressRepository;
//		private final ModelMapper modelMapper;		
//		
//		public ProgressService(ProgressRepository progressRepository, ModelMapper modelMapper) {
//			this.modelMapper = modelMapper;
//			this.progressRepository = progressRepository;
//			
//			
//		}
//		
//		
//		public Page<ProgressDTO> selectProgress(int page) {
//			
//			Pageable pageable = PageRequest.of(page -1, 10, Sort.by("dpNo").descending());
//			
//			
//			Page<Progress> ProgressList = progressRepository.findAll(pageable);
//			
//			Page<ProgressDTO> ProgressDTOList = ProgressList.map(progress -> modelMapper.map(progress, ProgressDTO.class));
//			
//		
//			return ProgressDTOList;
//		
//	}
//		
//}