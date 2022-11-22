package com.project.office.room.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.office.room.entity.Room;



public interface RoomRepository extends JpaRepository<Room, Long>{

	/* 1. 회의실 조회 (회원)/ 3. 회의실 조회(관리자) */
	Page<Room> findAll(Pageable pageable);
	
	/* 2. 회의실 상세조회 (회원) / 4. 회의실 상세 조회(관리자) */
	@Query("SELECT r " +
			 "FROM Room r " +
			"WHERE r.roomNo = :roomNo " )
	Optional<Room> findByRoomNo(@Param("roomNo")Long roomNo);

	
}
