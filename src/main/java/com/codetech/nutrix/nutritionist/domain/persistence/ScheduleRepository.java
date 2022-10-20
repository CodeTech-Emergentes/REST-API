package com.codetech.nutrix.nutritionist.domain.persistence;

import com.codetech.nutrix.nutritionist.domain.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "select * from schedule where id in(Select b.schedule_id From nutritionist_schedule b Where b.nutritionist_id = :nutritionist_id)", nativeQuery = true)
    List<Schedule> findByNutritionistId(@Param("nutritionist_id") Long nutritionist_id);

}
