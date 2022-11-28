package com.project.office.calendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.calendar.entity.Schedule;
import com.project.office.member.entity.Member;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

//	List<Schedule> findByMemberAndScheduleSort(Member member, String scheduleSort);

	Schedule findByScheduleNo(Long scheduleNo);

	List<Schedule> findByMember(Member member);

}
