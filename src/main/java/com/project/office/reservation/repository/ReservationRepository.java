package com.project.office.reservation.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.office.reservation.entity.Reservation;




public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Page<Reservation> findAll(Pageable pageable);

	@Query("SELECT re " +
			 "FROM Reservation re " +
			"WHERE re.reservationNo = :reservationNo " +
			 "AND re.reservationStatus = '예약가능'")
	Optional<Reservation> findByReservationNo(@Param("reservationNo")Long reservationNo);
	
	/* 1-1. 예약 목록 조회 - 검색[예약중 / 예약 가능/ 예약 취소]*/
	Page<Reservation> findByReservationStatus(Pageable pageable, String reservationStatus);
	
	

	

}
