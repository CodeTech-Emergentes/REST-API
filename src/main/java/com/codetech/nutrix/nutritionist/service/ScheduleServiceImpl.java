package com.codetech.nutrix.nutritionist.service;

import com.codetech.nutrix.nutritionist.domain.model.entity.Schedule;
import com.codetech.nutrix.nutritionist.domain.persistence.ScheduleRepository;
import com.codetech.nutrix.nutritionist.domain.service.ScheduleService;
import com.codetech.nutrix.shared.exception.ResourceNotFoundException;
import com.codetech.nutrix.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final static String ENTITY = "Schedule";

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule create(Schedule schedule) {
        Set<ConstraintViolation<Schedule>> violations = validator.validate(schedule);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, scheduleId));
    }
}
