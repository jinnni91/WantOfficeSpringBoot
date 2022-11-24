package com.project.office.approval.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.approval.dto.DocumentDTO;
import com.project.office.approval.entity.Document;
import com.project.office.approval.repository.DocumentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocumentService {
	
	private DocumentRepository documentRepository;
	private final ModelMapper modelMapper;

	
	public DocumentService(DocumentRepository documentRepository, ModelMapper modelMapper) {
		this.documentRepository = documentRepository;
		this.modelMapper = modelMapper;
	}
	
	public Page<DocumentDTO> selectDocumentList(int page) {
		
		log.info("[ProductService] selectDocumentList Start =====================" );

		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("docNo").descending());
		Page<Document> DocumentList = documentRepository.findByDocStatus(pageable,"Y");
		Page<DocumentDTO> DocumentDTOList = DocumentList.map(product -> modelMapper.map(product, DocumentDTO.class));
		
		log.info("[ProductService] productDtoList : {}", DocumentDTOList.getContent());
		
		log.info("[ProductService] selectDocumentList End =====================" );

		
		return DocumentDTOList;
	}
	
	
	

	
	
	

}
