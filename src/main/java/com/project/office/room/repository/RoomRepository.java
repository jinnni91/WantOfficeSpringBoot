package com.project.office.room.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.room.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

	/*1. 회의실 조회 */
	@EntityGraph(attributePaths= {"roomNo"})
	Page<Room> findByRoomLocation(Pageable pageable, String roomLocation);

	

}
