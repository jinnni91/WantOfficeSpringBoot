package com.project.office.approval.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.approval.entity.Document;
import com.project.office.approval.entity.Progress;

public interface DocumentRepository extends JpaRepository<Document, Long>{

	
	// 기안자 : 결재 조회
//	@EntityGraph(attributePaths= {"progress","member"}, type = EntityGraph.EntityGraphType.LOAD)
	
	
//	Page<Document> findByDocStatus(Pageable pageable, String docStatus);

//	@EntityGraph(attributePaths= {"progress"})
	//Page<Document> findByprogressAndDocStatus(Pageable pageable, Optional<Progress> findProgress ,String docStatus);

	
	
//	@EntityGraph(attributePaths= {"progress"})
//	Page<Document> findByAll(Pageable pageable);
////	
//	Document findByAll();

	
}
