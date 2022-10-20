package com.codetech.nutrix.nutritionist.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="nutritionist_schedule")
@NoArgsConstructor
@AllArgsConstructor
public class NutritionistSchedule {

    @EmbeddedId
    private NutritionistScheduleFK nutritionistScheduleFK;

    @ManyToOne
    @MapsId("nutritionistId")
    private Nutritionist nutritionist;

    @ManyToOne
    @MapsId("scheduleId")
    private Schedule schedule;
}
