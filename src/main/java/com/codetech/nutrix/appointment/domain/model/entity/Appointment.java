package com.codetech.nutrix.appointment.domain.model.entity;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.codetech.nutrix.patient.domain.model.entity.Patient;
import com.codetech.nutrix.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name ="appointments")
public class Appointment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String meetUrl;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String Motive;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String PersonalHistory;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String TestRealized;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String Treatment;

    @NotNull
    private String ScheduleDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nutritionist_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Nutritionist nutritionist;
}

