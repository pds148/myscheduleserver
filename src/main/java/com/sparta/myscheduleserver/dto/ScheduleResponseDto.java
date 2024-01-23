package com.sparta.myscheduleserver.dto;

import com.sparta.myscheduleserver.entity.Schedule;
import lombok.Getter;

import java.sql.Date;
import java.util.List;


@Getter
public class ScheduleResponseDto {
    private Long scheduleId;
    private String taskTitle;
    private String taskContent;
    private String manager;
    private String password;
    private Date date;

    public ScheduleResponseDto(Schedule schedule) {
    }

    public ScheduleResponseDto(Long scheduleId, String taskTitle, String taskContent, String manager, String password, String date) {
    }

    public ScheduleResponseDto(Long id, String taskTitle, String taskContent, String manager, String password, java.util.Date date) {
    }

    public ScheduleResponseDto(ScheduleResponseDto schedule) {
    }

    public static List<ScheduleResponseDto> fromEntities() {
        return null;
    }
}
