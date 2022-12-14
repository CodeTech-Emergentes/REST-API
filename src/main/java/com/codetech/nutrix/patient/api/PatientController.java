package com.codetech.nutrix.patient.api;

import com.codetech.nutrix.patient.domain.service.PatientService;
import com.codetech.nutrix.patient.mapping.PatientMapper;
import com.codetech.nutrix.patient.resource.CreatePatientResource;
import com.codetech.nutrix.patient.resource.PatientResource;
import com.codetech.nutrix.patient.resource.UpdatePatientResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Patient")
@RestController
@RequestMapping("/api/v1/patients")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper mapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.mapper = patientMapper;
    }

    @Operation(summary = "Get Patients", description = "Get All Patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Patients found"),
            @ApiResponse(responseCode = "400",description = "Patient not found") })
    @GetMapping
    public List<PatientResource> getAllPatients() {
        return mapper.toResource(patientService.getAll());
    }

    @Operation(summary = "Get Patients by Id", description = "Get Patient by Id")
    @GetMapping("{patientId}")
    public PatientResource getById(@PathVariable Long patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }


    @Operation(summary = "Get Patients by Email", description = "Get Patient information by email")
    @GetMapping("/email/{patientEmail}")
    public PatientResource getPatientByEmail(@PathVariable String patientEmail) {
        return mapper.toResource(patientService.getByEmail(patientEmail));
    }

    //create patient
    @Operation(summary = "Create Patient", description = "Create Patient")
    @PostMapping
    public PatientResource createPatient( @RequestBody CreatePatientResource request) {
        return mapper.toResource(patientService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update patient", description = "Update Patient by Id ")
    @PutMapping("{patientId}")
    public PatientResource updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientResource request) {
        return mapper.toResource(patientService.update(patientId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete patient", description = "Delete Patient by Id")
    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deletePost(@PathVariable Long patientId) {
        return patientService.delete(patientId);
    }
}
