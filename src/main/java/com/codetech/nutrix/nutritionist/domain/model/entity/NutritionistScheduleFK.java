package com.codetech.nutrix.nutritionist.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class NutritionistScheduleFK implements Serializable {

    @Column(name = "nutritionist_id", nullable = false)
    private Long nutritionistId;

    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;
}
