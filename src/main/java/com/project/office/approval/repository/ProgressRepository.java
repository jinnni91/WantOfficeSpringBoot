package com.project.office.approval.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.approval.entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress,Long> {

//		Progress findByAll();
	
	
		Optional<Progress> findByDpNo(Long dpNo);
		
}
