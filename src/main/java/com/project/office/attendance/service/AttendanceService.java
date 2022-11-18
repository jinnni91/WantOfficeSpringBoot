package com.project.office.attendance.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.office.attendance.dto.AttendanceDTO;
import com.project.office.attendance.entity.Attendance;
import com.project.office.attendance.repository.AttendanceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttendanceService {
	
	private final AttendanceRepository attendanceRepository;
	private final ModelMapper modelMapper;
	
	public AttendanceService(AttendanceRepository attendanceRepository, ModelMapper modelMapper) {
		this.attendanceRepository = attendanceRepository;
		this.modelMapper = modelMapper;
	}
	
	@Transactional
	public AttendanceDTO insertAttIn(AttendanceDTO attendanceDTO) throws ParseException {
		
		log.info("[AttendanceService] insertAttIn Start ====================");
		log.info("[AttendanceService] attendanceDTO : {}", attendanceDTO);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH24:MI:SS");
		Date attIn = attendanceDTO.getAttIn();
		Date tardy = sdf.parse("09:30:00");
		
		if (attIn.compareTo(tardy) > 0) {
	        log.info("[AttendanceService] 지각, 출근시간 : {}", attIn);
	        attendanceDTO.setAttType("지각");
	        
	    } else if(attIn.compareTo(tardy) < 0) {
	    	log.info("[AttendanceService] 정상, 출근시간 : {}", attIn);
	    	attendanceDTO.setAttType("정상");
	    	
	    }
		
		attendanceRepository.save(modelMapper.map(attendanceDTO, Attendance.class));
		
		log.info("[AttendanceService] insertAttIn End ====================");
	
		return attendanceDTO;
		
	}

	@Transactional
	public AttendanceDTO insertAttOut(AttendanceDTO attendanceDTO) throws ParseException {
		
		log.info("[AttendanceService] insertAttOut Start ====================");
		log.info("[AttendanceService] attendanceDTO : {}", attendanceDTO);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH24:MI:SS");
		Date attOut = attendanceDTO.getAttOut();
		Date leaveEarly = sdf.parse("17:00:00");
		
		if (attOut.compareTo(leaveEarly) > 0) {
	        log.info("[AttendanceService] 정상, 퇴근시간 : {}", attOut);
	        attendanceDTO.setAttType("정상");
	        
	    } else if(attOut.compareTo(leaveEarly) < 0) {
	    	log.info("[AttendanceService] 조퇴, 퇴근시간 : {}", attOut);
	    	attendanceDTO.setAttType("조퇴");
	    	
	    }
		
		attendanceRepository.save(modelMapper.map(attendanceDTO, Attendance.class));
		
		log.info("[AttendanceService] insertAttOut End ====================");
		
		return attendanceDTO;
		
	}

}
