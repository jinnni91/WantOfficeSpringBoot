package com.project.office.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	

}
