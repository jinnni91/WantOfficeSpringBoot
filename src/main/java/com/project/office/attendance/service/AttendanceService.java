package com.project.office.attendance.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.office.attendance.dto.AttendanceDTO;
import com.project.office.attendance.entity.Attendance;
import com.project.office.attendance.repository.AttendanceRepository;
import com.project.office.member.dto.MemberDTO;

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
	
	/* 출근 등록 */
	@Transactional
	public AttendanceDTO insertAttIn(AttendanceDTO attendanceDTO) throws ParseException {
		
		log.info("[AttendanceService] insertAttIn Start ====================");
		log.info("[AttendanceService] attendanceDTO : {}", attendanceDTO);
		
		attendanceRepository.save(modelMapper.map(attendanceDTO, Attendance.class));
		
		log.info("[AttendanceService] insertAttIn End ====================");
	
		return attendanceDTO;
		
	}

	/* 퇴근 등록 */
	@Transactional
	public AttendanceDTO insertAttOut(AttendanceDTO attendanceDTO,LocalDateTime now) throws ParseException {
		
		log.info("[AttendanceService] insertAttOut Start ====================");
		log.info("[AttendanceService] attendanceDTO : {}", attendanceDTO);
		
		// LocalDateTime 타입으로 비교
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime start = LocalDate.now().atStartOfDay();
		
		// Member와 오늘 날짜로 출근 찍힌 행 기준으로 조회
		Attendance oriAttendance = attendanceRepository.findByMemberAndAttIn(today, start, attendanceDTO.getMember().getMemberNo())
				.orElseThrow(() -> new RuntimeException("오늘 출근 시간이 존재하지 않습니다."));
		
		oriAttendance.setAttOut(now);
		log.info("[AttendanceService] oriAttendance : {}", oriAttendance);
		log.info("[AttendanceService] setAttOut : {}", oriAttendance.getAttOut());
		
		attendanceRepository.save(oriAttendance);
		
		log.info("[AttendanceService] insertAttOut End ====================");
		
		return modelMapper.map(oriAttendance, AttendanceDTO.class);
		
	}

	/* 내 근태 월별 목록 조회 */
	public Page<AttendanceDTO> getMyAttList(MemberDTO member, int page, String attDate) {
		
		log.info("[AttendanceService] getMyAttList Start ====================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("attDate").descending());
		
		Long memberNo = member.getMemberNo();
		
		LocalDate date = LocalDate.parse(attDate);
		YearMonth month = YearMonth.from(date);
		
		LocalDate firstDate = month.atDay(1);
		LocalDate lastDate = month.atEndOfMonth();
		
		String firstDateString = firstDate.toString();
		String lastDateString = lastDate.toString();
		
		Page<Attendance> attendanceList = attendanceRepository.findByAttDateMonth(memberNo, firstDateString, lastDateString, pageable);
		Page<AttendanceDTO> attendanceDTOList = attendanceList.map(attendance -> modelMapper.map(attendance, AttendanceDTO.class));
		
		log.info("[AttendanceService] attendanceList : {}", attendanceList);
		log.info("[AttendanceService] attendanceDTOList : {}", attendanceDTOList);
		
		log.info("[AttendanceService] getMyAttList End ====================");
		
		return attendanceDTOList;
		
	}

	/* 날짜별 근태 목록 조회(관리자) */
	public Page<AttendanceDTO> getAttListForAdmin(int page, String attDate) {
		
		log.info("[AttendanceService] getAttListForAdmin Start ====================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("member").ascending());
		
		Page<Attendance> attendanceList = attendanceRepository.findByAttDate(pageable, attDate);
		Page<AttendanceDTO> attendanceDTOList = attendanceList.map(attendance -> modelMapper.map(attendance, AttendanceDTO.class));
		
		log.info("[AttendanceService] attendanceDTOList : {}, attendanceDTOList");
		
		log.info("[AttendanceService] getAttListForAdmin End ====================");
		
		return attendanceDTOList;
		
	}

}
