package com.codetech.nutrix.appointment.service;

import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import com.codetech.nutrix.nutritionist.domain.persistence.NutritionistRepository;
import com.codetech.nutrix.appointment.domain.model.entity.Appointment;
import com.codetech.nutrix.appointment.domain.persistance.AppointmentRepository;
import com.codetech.nutrix.appointment.domain.service.AppointmentService;
import com.codetech.nutrix.patient.domain.model.entity.Patient;
import com.codetech.nutrix.patient.domain.persistence.PatientRepository;
import com.codetech.nutrix.shared.exception.ResourceNotFoundException;
import com.codetech.nutrix.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final static String ENTITY = "Appointment";

    private final AppointmentRepository appointmentRepository;
    private final Validator validator;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private NutritionistRepository nutritionistRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, Validator validator) {
        this.appointmentRepository = appointmentRepository;
        this.validator = validator;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }


    @Override
    public Appointment getById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public Appointment create(Appointment request, Long nutritionistId, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found with Id " + patientId));
        Nutritionist nutritionist = nutritionistRepository.findById(nutritionistId).orElseThrow(() -> new ResourceNotFoundException("Nutritionist not found with Id " + nutritionistId));
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        request.setPatient(patient);
        request.setNutritionist(nutritionist);
        return appointmentRepository.save(request);
    }

    @Override
    public Appointment update(Long appointmentId, Appointment request) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return appointmentRepository.findById(appointmentId).map(appointment ->
                        appointmentRepository.save(
                                appointment.withMeetUrl(request.getMeetUrl())
                                        .withScheduleDate(request.getScheduleDate())
                                        .withPersonalHistory(request.getPersonalHistory())
                                        .withMotive(request.getMotive())
                                        .withTestRealized(request.getTestRealized())
                                        .withTreatment(request.getTreatment())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public List<Appointment> getByNutritionistId(Long nutritionistId) {
        return appointmentRepository.findByNutritionistId(nutritionistId);

    }

    @Override
    public List<Appointment> getByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getByPatientIdAndNutritionistId(Long patientId, Long nutritionistId) {
        return appointmentRepository.findByPatientIdAndNutritionistId(patientId, nutritionistId);
    }

    @Override
    public List<Patient> getPatientsByNutritionistId(Long nutritionistId) {
        return patientRepository.findPatientsByNutritionistId(nutritionistId);
    }

    @Override
    public ResponseEntity<?> delete(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).map(appointment -> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }
}
