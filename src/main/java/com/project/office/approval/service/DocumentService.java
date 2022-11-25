//package com.project.office.approval.service;
//
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import com.project.office.approval.dto.DocumentDTO;
//import com.project.office.approval.dto.ProgressDTO;
//import com.project.office.approval.entity.Document;
//import com.project.office.approval.entity.Progress;
//import com.project.office.approval.repository.DocumentRepository;
//import com.project.office.approval.repository.ProgressRepository;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class DocumentService {
//	
//	private DocumentRepository documentRepository;
//	private ProgressRepository progressRepository;
//	private final ModelMapper modelMapper;
//
//	
//	public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper,  ProgressRepository progressRepository) {
//		this.documentRepository = documentRepository;
//		this.progressRepository = progressRepository;
//		this.modelMapper = modelMapper;
//	}
//	
//	public Page<DocumentDTO> selectDocumentList(int page, ProgressDTO progress) {
//		
//		log.info("[ProductService] selectDocumentList Start =====================" );
//		
//		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("docNo").descending());
//		
//		Optional<Progress> findProgress = progressRepository.findById(progress.getDpNo());
//		
////		Progress findProgress = progressRepository.findBydocNO(docNo);
//	
//		Page<Document> DocumentList = documentRepository.findByprogressAndDocStatus(pageable, findProgress, "Y");
//		Page<DocumentDTO> DocumentDTOList = DocumentList.map(document -> modelMapper.map(document, DocumentDTO.class));
//		
//		log.info("[ProductService] productDtoList : {}", DocumentDTOList.getContent());
//		
//		log.info("[ProductService] selectDocumentList End =====================" );
//
//		
//		return DocumentDTOList;
//	}
//	
//	
//	
////	public Page<DocumentDTO> selectDocumentList(int page) {
////		
////		log.info("[ProductService] selectDocumentList Start =====================" );
////		
////		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("docNo").descending());
////		Page<Document> DocumentList = documentRepository.findByDocStatus(pageable,"Y");
////		Page<DocumentDTO> DocumentDTOList = DocumentList.map(product -> modelMapper.map(product, DocumentDTO.class));
////		
////		log.info("[ProductService] productDtoList : {}", DocumentDTOList.getContent());
////		
////		log.info("[ProductService] selectDocumentList End =====================" );
////
////		
////		return DocumentDTOList;
////	}
//	
//
//	
//	
//	
//
//}
