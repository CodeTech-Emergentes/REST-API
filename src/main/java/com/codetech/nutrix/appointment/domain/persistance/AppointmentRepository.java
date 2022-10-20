package com.codetech.nutrix.appointment.domain.persistance;

import com.codetech.nutrix.appointment.domain.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByNutritionistId(Long nutritionistId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByPatientIdAndNutritionistId(Long patientId, Long nutritionistId);
}
