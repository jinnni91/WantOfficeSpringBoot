package com.project.office.attendance.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.office.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	/* 내 근태 월별 목록 조회 */
	@EntityGraph(attributePaths= {"member"})
	@Query("SELECT a " +
			 "FROM Attendance a " +
			"WHERE a.attDate = :attDate " +
			 "AND a.member.memberNo = :memberNo"/*+
			 "BETWEEN TO_DATE('${a.attDate}', 'YYYY-MM-DD') " +
			  "AND LAST_DAY(TO_DATE('${a.attDate}', 'YYYY-MM-DD')"*/
			)
	Page<Attendance> findByMemberNoAndAttDate(Pageable pageable, Long memberNo, String attDate);

	/* 날짜별 근태 목록 조회(관리자) */
	@EntityGraph(attributePaths= {"member"})
	@Query("SELECT a " +
			 "FROM Attendance a " +
			"WHERE a.attDate = :attDate"
		   )
	Page<Attendance> findByAttDate(Pageable pageable, String attDate);

	@Query("SELECT a " +
			 "FROM Attendance a " +
			"WHERE a.attIn < :now and a.attIn > :past and a.member.memberNo = :memberNo"
			)
	Optional<Attendance> findByMemberAndAttDate(@Param("now") LocalDateTime today, @Param("past") LocalDateTime past, @Param("memberNo") Long memberNo);
	
}
