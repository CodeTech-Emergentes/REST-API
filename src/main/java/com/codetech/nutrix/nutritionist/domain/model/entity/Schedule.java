package com.codetech.nutrix.nutritionist.domain.model.entity;

import com.codetech.nutrix.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "schedule")
public class Schedule extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String time;

    @OneToMany(mappedBy = "schedule")
    private List<NutritionistSchedule> nutritionistSchedules;
}
