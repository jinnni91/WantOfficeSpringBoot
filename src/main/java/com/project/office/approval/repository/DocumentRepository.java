package com.project.office.approval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.approval.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

	
	// 기안자 : 결재 조회
	Page<Document> findByDocStatus(Pageable pageable, String docStatus);

	
}
