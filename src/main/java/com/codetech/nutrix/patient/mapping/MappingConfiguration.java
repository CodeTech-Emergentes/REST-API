package com.codetech.nutrix.patient.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("patientMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PatientMapper patientMapper() { return new PatientMapper(); }

}
