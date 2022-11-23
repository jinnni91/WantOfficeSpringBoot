package com.project.office.calendar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.office.calendar.Repository.ScheduleRepository;
import com.project.office.calendar.dto.ScheduleDTO;
import com.project.office.calendar.entity.Schedule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleService {
	
	private ScheduleRepository scheduleRepository;
	private ModelMapper modelMapper;

	public ScheduleService (ScheduleRepository scheduleRepository, ModelMapper modelMapper) {
		this.scheduleRepository = scheduleRepository;
		this.modelMapper = modelMapper;
	}
	
	public List<ScheduleDTO> scheduleList(String scheduleSort) {
		
//		log.info("[ScheduleService] scheduleSort : {} ", scheduleSort );
		
		List<Schedule> scheduleList = scheduleRepository.findByScheduleSort(scheduleSort);

//		log.info("[ScheduleService] scheduleList : {} ", scheduleList );
		
		return scheduleList.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());
	}

	public ScheduleDTO Selectschedule(Long scheduleNo) {

//		log.info("[ScheduleService] scheduleSort : {} ", scheduleNo );
		
		Schedule schedule = scheduleRepository.findByScheduleNo(scheduleNo);
		
//		log.info("[ScheduleService] schedule : {} ", schedule );
		
		return modelMapper.map(schedule, ScheduleDTO.class);
	}
	
	@Transactional
	public ScheduleDTO insertSchedule(ScheduleDTO scheduleDTO) {

//		log.info("[ScheduleService] schedule : {} ", scheduleDTO );
		
		scheduleRepository.save(modelMapper.map(scheduleDTO, Schedule.class));
		
		return scheduleDTO;
	}

	@Transactional
	public ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO) {

		log.info("[ScheduleService] scheduleDTO : {}", scheduleDTO);
		
		Schedule newSchedule = scheduleRepository.findByScheduleNo(scheduleDTO.getScheduleNo());
		
//		ScheduleDTO updateSchedule = modelMapper.map(newSchedule, ScheduleDTO.class);
		
		newSchedule.update(scheduleDTO.getScheduleTitle(),
				scheduleDTO.getScheduleStart(),
				scheduleDTO.getScheduleEnd(),
				scheduleDTO.getScheduleSort(),
				scheduleDTO.getScheduleColor(),
				scheduleDTO.getSchedulePlace(),
				scheduleDTO.getScheduleContent(),
				scheduleDTO.getDept());
		
		scheduleRepository.save(newSchedule);
		
		return scheduleDTO;
	}

//	public Object deleteSchedule(Long scheduleNo) {
//
//		return null;
//	}

}
