package com.sparta.myscheduleserver.repository;

import com.sparta.myscheduleserver.dto.ScheduleRequestDto;
import com.sparta.myscheduleserver.dto.ScheduleResponseDto;
import com.sparta.myscheduleserver.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (task_title, task_content, manager, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, schedule.getTaskTitle());
            preparedStatement.setString(2, schedule.getTaskContent());
            preparedStatement.setString(3, schedule.getManager());
            preparedStatement.setString(4, schedule.getPassword());
            preparedStatement.setDate(5, schedule.getDate());
            return preparedStatement;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        schedule.setScheduleId(id);

        return schedule;
    }

    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, new ScheduleRowMapper());
    }

    public ScheduleResponseDto findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";
        return jdbcTemplate.query(sql, new ScheduleRowMapper(), id).stream().findFirst().orElse(null);
    }

    public void update(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule SET task_title = ?, task_content = ?, manager = ?, password = ?, date = ? WHERE schedule_id = ?";
        jdbcTemplate.update(sql, requestDto.getTaskTitle(), requestDto.getTaskContent(), requestDto.getManager(),
                requestDto.getPassword(), requestDto.getDate(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ScheduleRowMapper implements RowMapper<ScheduleResponseDto> {
        @Override
        public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("schedule_id");
            String taskTitle = rs.getString("task_title");
            String taskContent = rs.getString("task_content");
            String manager = rs.getString("manager");
            String password = rs.getString("password");
            Date date = rs.getDate("date");
            return new ScheduleResponseDto(id, taskTitle, taskContent, manager, password, date);
        }
    }
}
