package com.sparta.myscheduleserver.entity;

import com.sparta.myscheduleserver.dto.ScheduleRequestDto;
import com.sparta.myscheduleserver.dto.ScheduleResponseDto;
import lombok.Getter;

import java.sql.Date;


@Getter
public class Schedule {
    private Long scheduleId;
    private String taskTitle;
    private String taskContent;
    private String manager;
    private String password;
    private Date date;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
    }

    public void setId(Long maxId) {
    }

    public void update(ScheduleResponseDto scheduleResponseDto) {
    }

    public void setScheduleId(Long id) {
    }
}
