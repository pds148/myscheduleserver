package com.sparta.myscheduleserver.dto;

import lombok.Getter;

import java.sql.Date;

@Getter
public class ScheduleRequestDto {
    private String taskTitle;
    private String taskContent;
    private String manager;
    private String password;
    private Date date;
}
