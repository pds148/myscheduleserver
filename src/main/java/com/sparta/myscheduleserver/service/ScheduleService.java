package com.sparta.myscheduleserver.service;

import com.sparta.myscheduleserver.dto.ScheduleRequestDto;
import com.sparta.myscheduleserver.dto.ScheduleResponseDto;
import com.sparta.myscheduleserver.entity.Schedule;
import com.sparta.myscheduleserver.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule);
    }

    public ScheduleResponseDto viewSelectedSchedule(Long id) {
        ScheduleResponseDto schedule = scheduleRepository.findById(id);
        if (schedule != null) {
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("Selected schedule does not exist.");
        }
    }

    public List<ScheduleResponseDto> viewScheduleList() {
        List<ScheduleResponseDto> schedules = scheduleRepository.findAll();
        return ScheduleResponseDto.fromEntities();
    }

    public Long editSelectedSchedule(Long id, ScheduleRequestDto requestDto) {
        ScheduleResponseDto schedule = scheduleRepository.findById(id);
        if (schedule != null) {
            scheduleRepository.update(id, requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("Selected schedule does not exist.");
        }
    }

    public Long deleteSelectedSchedule(Long id) {
        ScheduleResponseDto schedule = scheduleRepository.findById(id);
        if (schedule != null) {
            scheduleRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("Selected schedule does not exist.");
        }
    }
}
