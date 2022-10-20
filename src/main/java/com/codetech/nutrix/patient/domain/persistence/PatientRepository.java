package com.codetech.nutrix.patient.domain.persistence;

import com.codetech.nutrix.patient.domain.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    @Query(value = "select * from patients where id in (select patient_id from appointments where nutritionist_id = ?1)", nativeQuery = true)
    List<Patient> findPatientsByNutritionistId(Long nutritionistId);


}
