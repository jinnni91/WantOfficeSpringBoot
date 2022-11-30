package com.project.office.reservation.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.office.reservation.entity.Reservation;
import com.project.office.room.entity.Room;




public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	/* 1. 예약 목록 전체 조회(회원) */
	Page<Reservation> findAll(Pageable pageable);
	
	/* 2. 예약 목록 조회 - 검색[예약중 / 예약 가능/ 예약 취소]*/
	Page<Reservation> findByReservationStatus(Pageable pageable, String reservationStatus);
	
	/* 3. 예약 상세 조회(회원) */
	@Query("SELECT re " +
			 "FROM Reservation re " +
			"WHERE re.reservationNo = :reservationNo ")
	Optional<Reservation> findByReservationNo(@Param("reservationNo")Long reservationNo);
	
	/* 3-1. 예약 조회(회의실) */
	@Query("SELECT re " +
			 "FROM Reservation re " +
			"WHERE re.reservationDate >= :start and re.reservationDate < :end and re.room.roomNo = :roomNo")
	Optional<List<Reservation>> findByRoomAndReservationDate(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end, @Param("roomNo")Long roomNo);

	
	
	
	

	

}
