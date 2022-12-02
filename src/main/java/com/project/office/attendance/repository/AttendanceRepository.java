package com.project.office.attendance.repository;

import java.time.LocalDateTime;
import java.util.List;
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
	@Query("select a from Attendance a where (a.attDate <= :lastDateString and a.attDate >= :firstDateString) and a.member.memberNo = :memberNo")
	List<Attendance> findByAttDateMonth(@Param("memberNo") Long memberNo, @Param("firstDateString") String firstDateString, @Param("lastDateString") String lastDateString);

	/* 날짜별 근태 목록 조회(관리자) */
	@EntityGraph(attributePaths= {"member"})
	@Query("select a from Attendance a where a.attDate = :attDate")
	Page<Attendance> findByAttDate(Pageable pageable, String attDate);

	/* 퇴근 등록을 위한 출근 조회 */
	@Query("select a from Attendance a where a.attIn < :now and a.attIn > :past and a.member.memberNo = :memberNo")
	Optional<Attendance> findByMemberAndAttIn(@Param("now") LocalDateTime today, @Param("past") LocalDateTime past, @Param("memberNo") Long memberNo);

	/* 출퇴근 조회 */
	@Query("select a from Attendance a where (a.attIn >= :start and a.attIn < :end and a.member.memberNo = :memberNo) or (a.attIn >= :start and a.attIn < :end and a.attOut >= :start and a.attOut < :end and a.member.memberNo = :memberNo)")
	Optional<Attendance> findByMemberAndAttInAndAttOut(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("memberNo") Long memberNo);

}
