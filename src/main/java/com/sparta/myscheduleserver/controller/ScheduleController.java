package com.sparta.myscheduleserver.controller;

import com.sparta.myscheduleserver.dto.ScheduleRequestDto;
import com.sparta.myscheduleserver.dto.ScheduleResponseDto;
import com.sparta.myscheduleserver.entity.Schedule;
import com.sparta.myscheduleserver.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto viewSelectedSchedule(@PathVariable Long id) {
        return scheduleService.viewSelectedSchedule(id);
    }

    @GetMapping
    public List<ScheduleResponseDto> viewScheduleList() {
        return scheduleService.viewScheduleList();
    }

    @PutMapping("/{id}")
    public Long editSelectedSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.editSelectedSchedule(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteSelectedSchedule(@PathVariable Long id) {
        return scheduleService.deleteSelectedSchedule(id);
    }
}
