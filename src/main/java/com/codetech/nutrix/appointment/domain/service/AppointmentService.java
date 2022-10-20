package com.codetech.nutrix.appointment.domain.service;

import com.codetech.nutrix.appointment.domain.model.entity.Appointment;
import com.codetech.nutrix.patient.domain.model.entity.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAll();
    Appointment getById(Long appointmentId);
    Appointment create(Appointment request, Long nutritionistId, Long patientId);
    Appointment update(Long appointmentId, Appointment request);
    List<Appointment> getByNutritionistId(Long nutritionistId);
    List<Appointment> getByPatientId(Long patientId);
    List<Appointment> getByPatientIdAndNutritionistId(Long patientId, Long nutritionistId);
    List<Patient> getPatientsByNutritionistId(Long nutritionistId);
    ResponseEntity<?> delete(Long appointmentId);
}
