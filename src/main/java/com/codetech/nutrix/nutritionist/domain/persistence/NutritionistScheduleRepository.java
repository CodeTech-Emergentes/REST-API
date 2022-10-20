package com.codetech.nutrix.nutritionist.domain.persistence;

import com.codetech.nutrix.nutritionist.domain.model.entity.NutritionistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionistScheduleRepository extends JpaRepository<NutritionistSchedule, Long> {

}
