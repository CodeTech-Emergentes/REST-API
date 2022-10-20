package com.codetech.nutrix.appointment.api;

import com.codetech.nutrix.appointment.domain.service.AppointmentService;
import com.codetech.nutrix.appointment.mapping.AppointmentMapper;
import com.codetech.nutrix.appointment.resource.AppointmentResource;
import com.codetech.nutrix.appointment.resource.CreateAppointmentResource;
import com.codetech.nutrix.appointment.resource.UpdateAppointmentResource;
import com.codetech.nutrix.patient.mapping.PatientMapper;
import com.codetech.nutrix.patient.resource.PatientResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Appointment")
@RestController
@RequestMapping("/api/v1/appointment")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper mapper;

    @Autowired
    private PatientMapper patientMapper;

    @Operation(summary = "Get all appointments", description = "Get All Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Appointments not found")})
    @GetMapping
    public List<AppointmentResource> getAllAppointments() {
        return mapper.toResource(appointmentService.getAll());
    }

    @Operation(summary = "Get Appointment by Id", description = "Get Appointment by Id")
    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Long appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

    @Operation(summary = "Get all appointments by patientId", description = "Get all appointments by patientId")
    @GetMapping("/patient/{patientId}")
    public List<AppointmentResource> getAllAppointmentsByPatientId(@PathVariable Long patientId) {
        return mapper.toResource(appointmentService.getByPatientId(patientId));
    }

    @Operation(summary = "Get all appointments by nutritionistId", description = "Get all appointments by nutritionistId")
    @GetMapping("/nutritionist/{nutritionistId}")
    public List<AppointmentResource> getAllAppointmentsByNutritionistId(@PathVariable Long nutritionistId) {
        return mapper.toResource(appointmentService.getByNutritionistId(nutritionistId));
    }

    // get patients by nutritionistId
    @Operation(summary = "Get all patients by nutritionistId", description = "Get all patients by nutritionistId")
    @GetMapping("/nutritionist/{nutritionistId}/patient")
    public List<PatientResource> getAllPatientsByNutritionistId(@PathVariable Long nutritionistId) {
        return patientMapper.toResource(appointmentService.getPatientsByNutritionistId(nutritionistId));
    }


    @Operation(summary = "Get all appointments by nutritionistId and patientId", description = "Get all appointments by nutritionistId and patientId")
    @GetMapping("/nutritionist/{nutritionistId}/patient/{patientId}")
    public List<AppointmentResource> getAllAppointmentsByNutritionistIdAndPatientId(@PathVariable Long nutritionistId, @PathVariable Long patientId) {
        return mapper.toResource(appointmentService.getByPatientIdAndNutritionistId(patientId, nutritionistId));
    }

    @Operation(summary = "Create appointment", description = "Create appointment")
    @PostMapping("/patient/{patientId}/nutritionist/{nutritionistId}")
    public AppointmentResource createAppointment(@PathVariable(name = "patientId") Long patientId,
                                                 @PathVariable(name = "nutritionistId") Long nutritionistId, @Valid @RequestBody CreateAppointmentResource request) {
        return mapper.toResource(appointmentService.create(mapper.toModel(request), nutritionistId, patientId));
    }

    @Operation(summary = "Update appointment", description = "Update appointment by Id ")
    @PutMapping("{appointmentId}")
    public AppointmentResource updateAppointment(@PathVariable Long appointmentId, @Valid @RequestBody UpdateAppointmentResource request) {
        return mapper.toResource(appointmentService.update(appointmentId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete appointment", description = "Delete appointment by Id")
    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId) {
        return appointmentService.delete(appointmentId);
    }
}
