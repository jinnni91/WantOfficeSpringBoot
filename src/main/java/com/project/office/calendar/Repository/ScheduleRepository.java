package com.project.office.calendar.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.office.calendar.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

	List<Schedule> findByScheduleSort(String scheduleSort);

	Schedule findByScheduleNo(Long scheduleNo);

}
