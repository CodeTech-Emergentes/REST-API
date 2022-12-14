package com.codetech.nutrix.appointment.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class UpdateAppointmentResource {

    private String meetUrl;
    private String Motive;
    private String PersonalHistory;
    private String TestRealized;
    private String Treatment;
    private String ScheduleDate;
}
