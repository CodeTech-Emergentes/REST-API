package com.codetech.nutrix.appointment.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResource {
    private Long id;
    private String meetUrl;
    private String Motive;
    private String PersonalHistory;
    private String TestRealized;
    private String Treatment;
    private String ScheduleDate;
    private Long nutritionistId;
    private Long patientId;
}
