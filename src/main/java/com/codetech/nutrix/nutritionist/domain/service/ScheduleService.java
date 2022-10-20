package com.codetech.nutrix.nutritionist.domain.service;

import com.codetech.nutrix.nutritionist.domain.model.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAll();
    Schedule create(Schedule schedule);
    Schedule getById(Long scheduleId);
}
